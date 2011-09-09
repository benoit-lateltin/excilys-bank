package com.excilys.ebi.bank.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.web.messages.FlashMessages;
import com.excilys.ebi.bank.web.security.LoginSuccessHandler;

@Controller
public class LoginController {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private FlashMessages messages;

	@RequestMapping("/public/login.html")
	public String login(ModelMap model, HttpSession session) {

		// hack : can't check SecurityContextHolder has login.html is not
		// protected by Spring Security
		if (session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {
			return "redirect:" + loginSuccessHandler.getHomeUrl();
		}

		return "public/login";
	}

	@RequestMapping("/public/loginFailure.html")
	public String loginFailure(ModelMap model, HttpSession session, HttpServletResponse res) {

		Exception loginException = Exception.class.cast(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));

		handleException(loginException);

		return "redirect:/public/login.html";
	}

	private void handleException(Exception loginException) {

		if (loginException instanceof BadCredentialsException) {
			messages.add("message.error.login.badCredentials");

		} else {
			Throwable cause = ExceptionUtils.getRootCause(loginException);
			messages.add("message.error.login", cause != null ? cause.getMessage() : loginException.getMessage());
		}
	}
}
