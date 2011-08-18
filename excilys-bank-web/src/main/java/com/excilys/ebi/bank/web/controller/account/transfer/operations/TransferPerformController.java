package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import static com.google.common.collect.Collections2.filter;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.service.UnsufficientBalanceException;
import com.excilys.ebi.bank.web.interceptor.account.AccountController;
import com.excilys.ebi.bank.web.interceptor.page.Page;
import com.excilys.ebi.bank.web.interceptor.page.PageController;
import com.excilys.ebi.bank.web.messages.FlashMessages;
import com.excilys.ebi.bank.web.security.SecurityUtils;
import com.excilys.ebi.utils.spring.log.slf4j.InjectLogger;
import com.google.common.base.Predicate;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}/transfers/perform.html")
public class TransferPerformController implements AccountController, PageController {

	@InjectLogger
	private Logger logger;

	@Autowired
	private BankService bankService;

	@Autowired
	private FlashMessages messages;

	@Override
	public Page getPage() {
		return Page.TRANSFER_PERFORM;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// accept "," as a decimal separator
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true) {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				super.setAsText(StringUtils.replaceChars(text, ',', '.'));
			}
		});
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayForm(@PathVariable String accountNumber, final @ModelAttribute TransferCommand command, ModelMap model) {

		List<Account> accounts = bankService.findAccountsByUser(SecurityUtils.getCurrentUser());
		model.put("debitableAccounts", accounts);

		if (command.getDebitedAccountNumber() == null) {
			command.setDebitedAccountNumber(accountNumber);
		}

		model.put("creditableAccounts", filter(accounts, new Predicate<Account>() {
			@Override
			public boolean apply(Account input) {
				return !input.getNumber().equals(command.getDebitedAccountNumber());
			}
		}));

		return "private/bank/account/transfers/perform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String performTransfer(@PathVariable String accountNumber, @ModelAttribute @Valid TransferCommand command, BindingResult result, ModelMap model) {

		if (!result.hasErrors()) {
			try {
				bankService.performTransfer(command.getDebitedAccountNumber(), command.getCreditedAccountNumber(), command.getAmount());

				messages.add("message.transfer.done");

				return "redirect:/private/bank/accounts.html";

			} catch (UnsufficientBalanceException e) {
				logger.info("insufficient amount");
				result.rejectValue("amount", "error.transfer.amount.unsufficientBalance");
			}

		}

		return displayForm(accountNumber, command, model);
	}
}