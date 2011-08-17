package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.web.interceptor.account.AccountController;
import com.excilys.ebi.bank.web.interceptor.page.Page;
import com.excilys.ebi.bank.web.interceptor.page.PageController;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}")
public class TransferOperationsController implements AccountController, PageController {

	@Override
	public Page getPage() {
		return Page.TRANSFER_OPERATIONS;
	}

	@RequestMapping("/transfers/operations.html")
	public String resolvedCardOperations(@PathVariable String accountNumber, ModelMap model) {

		return "private/bank/account/transfers/operations";
	}
}