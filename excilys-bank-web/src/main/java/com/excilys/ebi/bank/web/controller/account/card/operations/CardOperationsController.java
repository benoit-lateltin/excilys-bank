package com.excilys.ebi.bank.web.controller.account.card.operations;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.account.AccountController;
import com.excilys.ebi.bank.web.interceptor.calendar.CalendarController;
import com.excilys.ebi.bank.web.interceptor.page.Page;
import com.excilys.ebi.bank.web.interceptor.page.PageController;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}")
public class CardOperationsController implements AccountController, PageController, CalendarController {

	public static final String ALL_CARDS = "all";

	@Autowired
	private BankService bankService;

	@Override
	public Page getPage() {
		return Page.CARD_OPERATIONS;
	}

	@ModelAttribute("selectedCardNumber")
	public String getSelectedCardNumber(@PathVariable String cardNumber) {
		return cardNumber != null ? cardNumber : ALL_CARDS;
	}

	@RequestMapping("/cards/{cardNumber}/year/{year}/month/{month}/operations.html")
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

	@RequestMapping("/cards/{cardNumber}/pending/operations.html")
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
}