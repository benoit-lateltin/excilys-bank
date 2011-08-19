package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTable;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTableConverter;
import com.excilys.ebi.bank.web.interceptor.account.AccountModelAttribute;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}/transfers")
@WebPageModelAttribute(WebPage.TRANSFER_OPERATIONS)
public class TransferOperationsController {

	@Autowired
	protected BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/operations.html")
	@AccountModelAttribute
	public String resolvedCardOperations(@PathVariable String accountNumber, ModelMap model) {
		return "private/bank/account/transfers/operations";
	}

	@RequestMapping("/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable resolvedOperations(@PathVariable String accountNumber, @PathVariable int page) {

		Page<Operation> operations = bankService.findTransferOperationsByAccountNumber(accountNumber, page);

		return converter.convert(operations);
	}
}