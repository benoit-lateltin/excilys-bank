package com.excilys.ebi.bank.service;

import com.excilys.ebi.bank.model.entity.User;

public interface UserService {

	User findByLoginFetchRoles(String login);
}
