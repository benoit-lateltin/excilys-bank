package com.excilys.ebi.bank.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateMidnight;
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
import com.excilys.ebi.bank.model.Range;
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

@Service
@Transactional(readOnly = true)
public class BankServiceImpl implements BankService {

	private static final int PAGE_SIZE = 20;

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
	public List<Account> findAccountsByUser(User user) {
		return accountDao.findByUsers(user);
	}

	@Override
	public List<Account> findAccountsByUserFetchCards(User user) {
		return accountDao.findByUserFetchCards(user);
	}

	@Override
	@PostAuthorize("hasPermission(returnObject, 'read')")
	public Account findAccountByNumberFetchCards(String number) {
		return accountDao.findByNumberFetchCards(number);
	}

	private Range<DateTime> buildDateRange(int year, int monthOfYear) {

		// first midnight in this month
		DateMidnight from = new DateMidnight().withDayOfMonth(1).withYear(year).withMonthOfYear(monthOfYear);

		// last midnight in this month
		DateMidnight to = from.plusMonths(1).minusDays(1);

		return new Range<DateTime>(from.toDateTime(), to.toDateTime());
	}

	@Override
	public Page<Operation> findNonCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.findNonCardByAccountNumberAndDateRange(accountNumber, range, pageable);
	}

	@Override
	public List<Card> findCardsByAccountNumber(String accountNumber) {
		return cardDao.findByAccountNumber(accountNumber);
	}

	@Override
	public Collection<Operation> sumCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear) {

		final Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.sumResolvedAmountByAccountNumberAndDateRangeGroupByCard(accountNumber, range);
	}

	@Override
	public BigDecimal sumResolvedAmountByAccountNumberAndMonthAndSign(String accountNumber, int year, int monthOfYear, OperationSign sign) {

		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.sumResolvedAmountByAccountNumberAndDateRangeAndSign(accountNumber, range, sign);
	}

	@Override
	public Page<Operation> findResolvedCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.findCardOperationsByAccountNumberAndDateRangeAndStatus(accountNumber, range, OperationStatus.RESOLVED, pageable);
	}

	@Override
	public BigDecimal sumResolvedCardAmountByAccountNumberAndMonthAndSign(String accountNumber, int year, int monthOfYear, OperationSign sign) {

		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.sumCardAmountByAccountNumberAndDateRangeAndSignAndStatus(accountNumber, range, sign, OperationStatus.RESOLVED);
	}

	@Override
	public Page<Operation> findResolvedCardOperationsByCardNumberAndMonth(String cardNumber, int year, int monthOfYear, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.findCardOperationsByCardNumberAndDateRangeAndStatus(cardNumber, range, OperationStatus.RESOLVED, pageable);
	}

	@Override
	public BigDecimal sumResolvedCardAmountByCardNumberAndMonthAndSign(String cardNumber, int year, int monthOfYear, OperationSign sign) {

		Range<DateTime> range = buildDateRange(year, monthOfYear);
		return operationDao.sumCardAmountByCardNumberAndDateRangeAndSignAndStatus(cardNumber, range, sign, OperationStatus.RESOLVED);
	}

	@Override
	public Page<Operation> findPendingCardOperationsByAccountNumber(String accountNumber, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByAccountNumberAndDateRangeAndStatus(accountNumber, null, OperationStatus.PENDING, pageable);
	}

	@Override
	public BigDecimal sumPendingCardAmountByAccountNumberAndSign(String accountNumber, OperationSign sign) {

		return operationDao.sumCardAmountByAccountNumberAndDateRangeAndSignAndStatus(accountNumber, null, sign, OperationStatus.PENDING);
	}

	@Override
	public Page<Operation> findPendingCardOperationsByCardNumber(String cardNumber, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findCardOperationsByCardNumberAndDateRangeAndStatus(cardNumber, null, OperationStatus.PENDING, pageable);
	}

	@Override
	public BigDecimal sumPendingCardAmountByCardNumberAndSign(String cardNumber, OperationSign sign) {

		return operationDao.sumCardAmountByCardNumberAndDateRangeAndSignAndStatus(cardNumber, null, sign, OperationStatus.PENDING);
	}

	@Override
	public boolean isClientOfAccountByAccountIdAndUserLogin(int id, String login) {
		long count = accountDao.countAccountsByIdAndUserLogin(id, login);
		Assert.isTrue(count <= 1);
		return count > 0;

	}

	@Override
	public Page<Operation> findTransferOperationsByAccountNumber(String accountNumber, int page) {

		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return operationDao.findTransferByAccountNumber(accountNumber, pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public void performTransfer(String debitedAccountNumber, String creditedAccountNumber, BigDecimal amount) throws UnsufficientBalanceException {

		Assert.hasLength(debitedAccountNumber);
		Assert.hasLength(creditedAccountNumber);
		Assert.notNull(amount);
		Assert.isTrue(amount.compareTo(BigDecimal.valueOf(10L)) > 0);
		Assert.isTrue(!StringUtils.equals(debitedAccountNumber, creditedAccountNumber));

		Account debitedAccount = accountDao.findByNumberFetchCards(debitedAccountNumber);
		Assert.notNull(debitedAccount, "unknown account");

		if (debitedAccount.getBalance().compareTo(amount) < 0) {
			throw new UnsufficientBalanceException();
		}

		Account creditedAccount = accountDao.findByNumberFetchCards(creditedAccountNumber);
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
}
