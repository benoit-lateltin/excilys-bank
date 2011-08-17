package com.excilys.ebi.bank.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;

public interface BankService {

	List<Account> findAccountsByUser(User user);

	List<Account> findAccountsByUserFetchCards(User user);

	Account findAccountByNumberFetchCards(String number);

	List<Card> findCardsByAccountNumber(String accountNumber);

	Page<Operation> findNonCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear, int page);

	Collection<Operation> sumCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear);

	BigDecimal sumResolvedAmountByAccountNumberAndMonthAndSign(String accountNumber, int year, int monthOfYear, OperationSign sign);

	Page<Operation> findResolvedCardOperationsByAccountNumberAndMonth(String accountNumber, int year, int monthOfYear, int page);

	BigDecimal sumResolvedCardAmountByAccountNumberAndMonthAndSign(String accountNumber, int year, int monthOfYear, OperationSign sign);

	Page<Operation> findResolvedCardOperationsByCardNumberAndMonth(String cardNumber, int year, int monthOfYear, int page);

	BigDecimal sumResolvedCardAmountByCardNumberAndMonthAndSign(String cardNumber, int year, int monthOfYear, OperationSign sign);

	Page<Operation> findPendingCardOperationsByAccountNumber(String accountNumber, int page);

	BigDecimal sumPendingCardAmountByAccountNumberAndSign(String accountNumber, OperationSign sign);

	Page<Operation> findPendingCardOperationsByCardNumber(String cardNumber, int page);

	BigDecimal sumPendingCardAmountByCardNumberAndSign(String cardNumber, OperationSign sign);

	boolean isClientOfAccountByAccountIdAndUserLogin(int id, String login);

	Page<Operation> findTransferOperationsByAccountNumber(String accountNumber, int page);

	void performTransfer(String debitedAccountNumber, String creditedAccountNumber, BigDecimal amount) throws UnsufficientBalanceException;
}
