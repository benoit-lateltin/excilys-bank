package com.excilys.ebi.bank.web.controller.account.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.service.BankService;

@Controller
public class AccountOperationsTableController {

	@Autowired
	protected BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/private/bank/account/{accountNumber}/year/{year}/month/{month}/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable paginateOperations(@PathVariable String accountNumber, @PathVariable int year, @PathVariable int month, @PathVariable int page) {

		Page<Operation> operationPage = bankService.findNonCardOperationsByAccountNumberAndMonth(accountNumber, year, month, page);

		return converter.convert(operationPage);
	}
}