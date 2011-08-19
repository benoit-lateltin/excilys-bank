package com.excilys.ebi.bank.web.controller;

import static com.google.common.collect.Collections2.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.ref.AccountCategory;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;
import com.excilys.ebi.bank.web.security.SecurityUtils;
import com.google.common.base.Predicate;

@Controller
@RequestMapping("/private/bank")
@WebPageModelAttribute(WebPage.ACCOUNTS)
public class BankController {

	@Autowired
	private BankService bankService;

	@RequestMapping("/accounts.html")
	public void home(ModelMap model) {

		List<Account> accounts = bankService.findAccountsByUserFetchCards(SecurityUtils.getCurrentUser());

		model.put("checkingAccounts", filterByCategory(accounts, AccountCategory.CHECKING));
		model.put("savingAccounts", filterByCategory(accounts, AccountCategory.SAVING));
	}

	private Collection<Account> filterByCategory(Collection<Account> accounts, final AccountCategory category) {
		return filter(accounts, new Predicate<Account>() {
			@Override
			public boolean apply(Account input) {
				return input.getType().getCategory().getId() == category;
			}
		});
	}
}
