package com.excilys.ebi.bank.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.Role;

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
		return SecurityContextHolder.getContext() != null //
				&& SecurityContextHolder.getContext().getAuthentication() != null //
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	public static boolean isWithRole(Role role) {
		return isAuthenticated() && getCurrentUser().getRoles().contains(role);
	}
}
