package com.excilys.ebi.bank.dao;

import com.excilys.ebi.bank.model.entity.User;

public interface UserDaoCustom {

	User findByLoginFetchRoles(String login);
}
