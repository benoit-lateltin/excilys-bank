package com.excilys.ebi.bank.web.controller.account.card.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTableConverter;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTable;

@Controller
public class CardOperationsTableController {

	@Autowired
	protected BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/private/bank/account/{accountNumber}/cards/{cardNumber}/year/{year}/month/{month}/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable resolvedOperations(@PathVariable String accountNumber, @PathVariable String cardNumber, @PathVariable int year, @PathVariable int month, @PathVariable int page) {

		Page<Operation> operations = null;

		if (!cardNumber.equals(CardOperationsController.ALL_CARDS)) {
			operations = bankService.findResolvedCardOperationsByCardNumberAndMonth(cardNumber, year, month, page);

		} else {
			Assert.notNull(accountNumber, "if no cardNumber, accountNumber is required");
			operations = bankService.findResolvedCardOperationsByAccountNumberAndMonth(accountNumber, year, month, page);
		}

		return converter.convert(operations);
	}

	@RequestMapping("/private/bank/account/{accountNumber}/cards/{cardNumber}/pending/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable pendingOperations(@PathVariable String accountNumber, @PathVariable String cardNumber, @PathVariable int page) {

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