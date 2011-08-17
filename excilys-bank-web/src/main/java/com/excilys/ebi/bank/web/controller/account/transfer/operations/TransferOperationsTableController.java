package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTable;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTableConverter;

@Controller
public class TransferOperationsTableController {

	@Autowired
	protected BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/private/bank/account/{accountNumber}/transfers/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable resolvedOperations(@PathVariable String accountNumber, @PathVariable int page) {

		Page<Operation> operations = bankService.findTransferOperationsByAccountNumber(accountNumber, page);

		return converter.convert(operations);
	}
}