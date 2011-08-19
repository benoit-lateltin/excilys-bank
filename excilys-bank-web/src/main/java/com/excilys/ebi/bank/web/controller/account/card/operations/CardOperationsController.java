package com.excilys.ebi.bank.web.controller.account.card.operations;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTable;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTableConverter;
import com.excilys.ebi.bank.web.interceptor.account.AccountModelAttribute;
import com.excilys.ebi.bank.web.interceptor.calendar.CalendarModelAttribute;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}/cards/{cardNumber}")
@WebPageModelAttribute(WebPage.CARD_OPERATIONS)
public class CardOperationsController {

	public static final String ALL_CARDS = "all";

	@Autowired
	private BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@ModelAttribute("selectedCardNumber")
	public String getSelectedCardNumber(@PathVariable String cardNumber) {
		return cardNumber != null ? cardNumber : ALL_CARDS;
	}

	@RequestMapping("/year/{year}/month/{month}/operations.html")
	@AccountModelAttribute
	@CalendarModelAttribute
	public String resolvedCardOperations(@PathVariable String accountNumber, @PathVariable String cardNumber, @PathVariable int year, @PathVariable int month, ModelMap model) {

		BigDecimal creditSum, debitSum = null;

		if (!cardNumber.equals(ALL_CARDS)) {
			creditSum = bankService.sumResolvedCardAmountByCardNumberAndMonthAndSign(cardNumber, year, month, OperationSign.CREDIT);
			debitSum = bankService.sumResolvedCardAmountByCardNumberAndMonthAndSign(cardNumber, year, month, OperationSign.DEBIT);

		} else {
			Assert.notNull(accountNumber, "if no cardNumber, accountNumber is required");
			creditSum = bankService.sumResolvedCardAmountByAccountNumberAndMonthAndSign(accountNumber, year, month, OperationSign.CREDIT);
			debitSum = bankService.sumResolvedCardAmountByAccountNumberAndMonthAndSign(accountNumber, year, month, OperationSign.DEBIT);
		}

		model.put("creditSum", creditSum);
		model.put("debitSum", debitSum);

		return "private/bank/account/cards/operations";
	}

	@RequestMapping("/pending/operations.html")
	@AccountModelAttribute
	@CalendarModelAttribute
	public String pendingCardOperations(@PathVariable String accountNumber, @PathVariable String cardNumber, ModelMap model) {

		BigDecimal creditSum, debitSum = null;

		if (!cardNumber.equals(ALL_CARDS)) {
			creditSum = bankService.sumPendingCardAmountByCardNumberAndSign(cardNumber, OperationSign.CREDIT);
			debitSum = bankService.sumPendingCardAmountByCardNumberAndSign(cardNumber, OperationSign.DEBIT);

		} else {
			Assert.notNull(accountNumber, "if no cardNumber, accountNumber is required");
			creditSum = bankService.sumPendingCardAmountByAccountNumberAndSign(accountNumber, OperationSign.CREDIT);
			debitSum = bankService.sumPendingCardAmountByAccountNumberAndSign(accountNumber, OperationSign.DEBIT);
		}

		model.put("creditSum", creditSum);
		model.put("debitSum", debitSum);

		return "private/bank/account/cards/operations";
	}

	@RequestMapping("/year/{year}/month/{month}/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable resolvedCardOperationsTable(@PathVariable String accountNumber, @PathVariable String cardNumber, @PathVariable int year, @PathVariable int month,
			@PathVariable int page) {

		Page<Operation> operations = null;

		if (!cardNumber.equals(CardOperationsController.ALL_CARDS)) {
			operations = bankService.findResolvedCardOperationsByCardNumberAndMonth(cardNumber, year, month, page);

		} else {
			Assert.notNull(accountNumber, "if no cardNumber, accountNumber is required");
			operations = bankService.findResolvedCardOperationsByAccountNumberAndMonth(accountNumber, year, month, page);
		}

		return converter.convert(operations);
	}

	@RequestMapping("/pending/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable pendingCardOperationsTable(@PathVariable String accountNumber, @PathVariable String cardNumber, @PathVariable int page) {

		Page<Operation> operations = null;

		if (!cardNumber.equals(CardOperationsController.ALL_CARDS)) {
			operations = bankService.findPendingCardOperationsByCardNumber(cardNumber, page);

		} else {
			Assert.notNull(accountNumber, "if no cardNumber, accountNumber is required");
			operations = bankService.findPendingCardOperationsByAccountNumber(accountNumber, page);
		}

		return converter.convert(operations);
	}
}