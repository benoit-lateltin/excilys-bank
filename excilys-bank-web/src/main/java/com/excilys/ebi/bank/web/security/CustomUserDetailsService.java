package com.excilys.ebi.bank.web.security;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.RoleRef;
import com.excilys.ebi.bank.service.UserService;
import com.google.common.base.Function;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByLoginFetchRoles(username);

		if (user == null) {
			throw new UsernameNotFoundException(username + " : cet utilisateur n'existe pas.");
		}

		return createUserDetails(user);
	}

	private UserDetails createUserDetails(User user) {

		List<GrantedAuthority> authorities = transform(user.getRoles(), new Function<RoleRef, GrantedAuthority>() {
			@Override
			public GrantedAuthority apply(RoleRef input) {
				return new SimpleGrantedAuthority(input.getId().name());
			}
		});

		return new CustomUserDetails(user, newArrayList(authorities));
	}
}