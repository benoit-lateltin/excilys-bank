package com.excilys.ebi.bank.web.interceptor.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.service.BankService;

public class AccountHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected BankService bankService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if (handler instanceof HandlerMethod && HandlerMethod.class.cast(handler).getBean() instanceof AccountController && !(modelAndView.getView() instanceof RedirectView)) {
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
