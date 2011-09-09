package com.excilys.ebi.bank.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/admin")
@WebPageModelAttribute(WebPage.ADMIN)
public class AdminController {

	@Autowired
	private BankService bankService;

	@RequestMapping("/admin.html")
	public void admin(ModelMap model) {

		model.put("userCount", bankService.countUsers());
		model.put("accountCount", bankService.countAccounts());
		model.put("operationCount", bankService.countOperations());
	}
}
