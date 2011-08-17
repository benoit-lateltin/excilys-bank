package com.excilys.ebi.bank.web.controller.account.operations;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.account.AccountController;
import com.excilys.ebi.bank.web.interceptor.calendar.CalendarController;
import com.excilys.ebi.bank.web.interceptor.page.Page;
import com.excilys.ebi.bank.web.interceptor.page.PageController;

@Controller
public class AccountOperationsController implements AccountController, PageController, CalendarController {

	@Autowired
	private BankService bankService;

	@Override
	public Page getPage() {
		return Page.ACCOUNT_OPERATIONS;
	}

	@RequestMapping({ "/private/bank/account/{accountNumber}/year/{year}/month/{month}/operations.html" })
	public String displayOperations(@PathVariable String accountNumber, @PathVariable int year, @PathVariable int month, ModelMap model) {

		Collection<Operation> cardSums = bankService.sumCardOperationsByAccountNumberAndMonth(accountNumber, year, month);
		BigDecimal creditSum = bankService.sumResolvedAmountByAccountNumberAndMonthAndSign(accountNumber, year, month, OperationSign.CREDIT);
		BigDecimal debitSum = bankService.sumResolvedAmountByAccountNumberAndMonthAndSign(accountNumber, year, month, OperationSign.DEBIT);

		model.put("cardSums", cardSums);
		model.put("creditSum", creditSum);
		model.put("debitSum", debitSum);

		return "private/bank/account/operations";
	}
}