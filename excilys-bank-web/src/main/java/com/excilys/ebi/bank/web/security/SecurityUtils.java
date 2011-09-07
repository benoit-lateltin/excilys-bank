package com.excilys.ebi.bank.web.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.Role;
import com.excilys.ebi.bank.model.entity.ref.RoleRef;

public class SecurityUtils {

	private SecurityUtils() {
		throw new UnsupportedOperationException();
	}

	public static User getCurrentUser() {
		if (isAuthenticated()) {
			return CustomUserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
		}
		return null;
	}

	public static boolean isAuthenticated() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		return ctx != null //
				&& ctx.getAuthentication() != null //
				&& ctx.getAuthentication().isAuthenticated();
	}

	public static boolean isWithRole(Role role) {

		if (isAuthenticated()) {
			for (RoleRef roleRef : getCurrentUser().getRoles()) {
				if (roleRef.getId() == role) {
					return true;
				}
			}
		}

		return false;
	}
}
