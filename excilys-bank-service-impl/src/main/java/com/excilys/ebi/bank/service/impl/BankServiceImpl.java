package com.excilys.ebi.bank.service.impl;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Maps.uniqueIndex;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.excilys.ebi.bank.dao.AccountDao;
import com.excilys.ebi.bank.dao.CardDao;
import com.excilys.ebi.bank.dao.OperationDao;
import com.excilys.ebi.bank.dao.OperationStatusRefDao;
import com.excilys.ebi.bank.dao.OperationTypeRefDao;
import com.excilys.ebi.bank.dao.UserDao;
import com.excilys.ebi.bank.model.IConstants;
import com.excilys.ebi.bank.model.YearMonth;
import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;
import com.excilys.ebi.bank.model.entity.ref.OperationStatusRef;
import com.excilys.ebi.bank.model.entity.ref.OperationType;
import com.excilys.ebi.bank.model.entity.ref.OperationTypeRef;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.service.UnsufficientBalanceException;
import com.google.common.base.Function;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;

@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {

	private static final int PAGE_SIZE = 20;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private OperationDao operationDao;

	@Autowired
	private CardDao cardDao;

	@Autowired
	private OperationStatusRefDao operationStatusDao;

	@Autowired
	private OperationTypeRefDao operationTypeDao;

	@Override
	@Cacheable(cacheName = IConstants.Cache.ENTITY_CACHE, keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"))
	public Integer findAccountIdByNumber(String accountNumber) {
		return accountDao.findByNumber(accountNumber).getId();
	}

	@Override
	@Cacheable(cacheName = IConstants.Cache.ENTITY_CACHE, keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"))
	public Integer findCardIdByNumber(String cardNumber) {
		return cardDao.findByNumber(cardNumber).getId();
	}

	@Override
	public List<Account> findAccountsByUser(User user) {
		return accountDao.findByUsers(user);
	}

	@Override
	public List<Account> findAccountsByUserFetchCards(User user) {
		return accountDao.findByUserFetchCards(user);
	}

	@Override
	@PostAuthorize("hasPermission(returnObject, 'read')")
	public Account findAccountByNumberFetchCards(String accountNumber) {
		return accountDao.findByNumberFetchCards(accountNumber);
	}

	@Override
	public Page<Operation> findNonCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findNonCardByAccountIdAndYearMonth(accountId, yearMonth, pageable);
	}

	@Override
	public Collection<Operation> sumCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth) {

		Collection<Card> cards = cardDao.findByAccountId(accountId);

		final Map<Integer, Operation> sumsIndexedByCardId = uniqueIndex(operationDao.sumResolvedAmountByAccountIdAndYearMonthGroupByCard(accountId, yearMonth),
				new Function<Operation, Integer>() {
					@Override
					public Integer apply(Operation input) {
						return input.getCard().getId();
					}
				});

		return transform(cards, new Function<Card, Operation>() {

			@Override
			public Operation apply(Card input) {

				Operation sum = sumsIndexedByCardId.get(input.getId());
				return Operation.newOperationBuilder().withCard(input).withAmount(sum != null ? sum.getAmount() : BigDecimal.ZERO).build();
			}
		});
	}

	@Override
	public BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign) {

		return operationDao.sumResolvedAmountByAccountIdAndYearMonthAndSign(accountId, yearMonth, sign);
	}

	@Override
	public Page<Operation> findResolvedCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByAccountIdAndYearMonthAndStatus(accountId, yearMonth, OperationStatus.RESOLVED, pageable);
	}

	@Override
	public BigDecimal sumResolvedCardAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign) {
		return operationDao.sumCardAmountByAccountIdAndYearMonthAndSignAndStatus(accountId, yearMonth, sign, OperationStatus.RESOLVED);
	}

	@Override
	public Page<Operation> findResolvedCardOperationsByCardIdAndYearMonth(Integer cardId, YearMonth yearMonth, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByCardIdAndYearMonthAndStatus(cardId, yearMonth, OperationStatus.RESOLVED, pageable);
	}

	@Override
	public BigDecimal sumResolvedCardAmountByCardIdAndYearMonthAndSign(Integer cardId, YearMonth yearMonth, OperationSign sign) {
		return operationDao.sumCardAmountByCardIdAndYearMonthAndSignAndStatus(cardId, yearMonth, sign, OperationStatus.RESOLVED);
	}

	@Override
	public Page<Operation> findPendingCardOperationsByAccountId(Integer accountId, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByAccountIdAndYearMonthAndStatus(accountId, null, OperationStatus.PENDING, pageable);
	}

	@Override
	public BigDecimal sumPendingCardAmountByAccountIdAndSign(Integer accountId, OperationSign sign) {

		return operationDao.sumCardAmountByAccountIdAndYearMonthAndSignAndStatus(accountId, null, sign, OperationStatus.PENDING);
	}

	@Override
	public Page<Operation> findPendingCardOperationsByCardId(Integer cardId, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByCardIdAndYearMonthAndStatus(cardId, null, OperationStatus.PENDING, pageable);
	}

	@Override
	public BigDecimal sumPendingCardAmountByCardIdAndSign(Integer cardId, OperationSign sign) {

		return operationDao.sumCardAmountByCardIdAndYearMonthAndSignAndStatus(cardId, null, sign, OperationStatus.PENDING);
	}

	@Override
	public boolean isClientOfAccountByAccountIdAndUserLogin(int id, String login) {
		long count = accountDao.countAccountsByIdAndUserLogin(id, login);
		Assert.isTrue(count <= 1);
		return count > 0;

	}

	@Override
	public Page<Operation> findTransferOperationsByAccountId(Integer accountId, int page) {
		return operationDao.findTransferByAccountId(accountId, null);
	}

	@Override
	@Transactional(readOnly = false)
	public void performTransfer(Integer debitedAccountId, Integer creditedAccountId, BigDecimal amount) throws UnsufficientBalanceException {

		Assert.notNull(debitedAccountId, "debitedAccountId is required");
		Assert.notNull(creditedAccountId, "creditedAccountId is required");
		Assert.notNull(amount, "amount is required");
		Assert.isTrue(amount.compareTo(BigDecimal.valueOf(10L)) >= 0, "amount must be >= 10");
		Assert.isTrue(!debitedAccountId.equals(creditedAccountId), "accounts must be different");

		Account debitedAccount = accountDao.findOne(debitedAccountId);
		Assert.notNull(debitedAccount, "unknown account");

		if (debitedAccount.getBalance().compareTo(amount) < 0) {
			throw new UnsufficientBalanceException();
		}

		Account creditedAccount = accountDao.findOne(creditedAccountId);
		Assert.notNull(creditedAccount, "unknown account");

		debitedAccount.setBalance(debitedAccount.getBalance().subtract(amount));
		creditedAccount.setBalance(creditedAccount.getBalance().add(amount));

		DateTime now = new DateTime();
		OperationStatusRef status = operationStatusDao.findOne(OperationStatus.RESOLVED);
		OperationTypeRef type = operationTypeDao.findOne(OperationType.TRANSFER);

		Operation debitOperation = new Operation.Builder().withName("transfert -" + amount).withAccount(creditedAccount).withAmount(amount.negate()).withDate(now)
				.withStatus(status).withType(type).build();
		Operation creditOperation = new Operation.Builder().withName("transfert +" + amount).withAccount(debitedAccount).withAmount(amount).withDate(now).withStatus(status)
				.withType(type).build();

		operationDao.save(debitOperation);
		operationDao.save(creditOperation);
	}

	@Override
	public long countUsers() {
		return userDao.count();
	}

	@Override
	public long countAccounts() {
		return accountDao.count();
	}

	@Override
	public long countOperations() {
		return operationDao.count();
	}
}
