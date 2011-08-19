package com.excilys.ebi.bank.web.interceptor.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.AnnotatedMethodHandlerInterceptor;

public class AccountModelAttributeHandlerInterceptor extends AnnotatedMethodHandlerInterceptor<AccountModelAttribute> {

	@Autowired
	protected BankService bankService;

	public AccountModelAttributeHandlerInterceptor() {
		super(AccountModelAttribute.class);
	}

	@Override
	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, ModelAndView modelAndView) throws Exception {
		if (!(modelAndView.getView() instanceof RedirectView)) {
			exportAccount(modelAndView.getModelMap());
		}
	}

	private void exportAccount(ModelMap model) {

		String accountNumber = String.class.cast(model.get("accountNumber"));
		Assert.notNull(accountNumber, "accountNumber required");

		Account account = bankService.findAccountByNumberFetchCards(accountNumber);
		model.addAttribute("account", account);
	}
}
