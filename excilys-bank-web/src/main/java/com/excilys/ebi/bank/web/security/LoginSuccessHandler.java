package com.excilys.ebi.bank.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.entity.ref.Role;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("error");
		return getHomeUrl();
	}

	public String getHomeUrl() {
		if (SecurityUtils.isWithRole(Role.ROLE_ADMIN)) {
			return "/private/admin/admin.html";
		}
		return "/private/bank/accounts.html";
	}
}
