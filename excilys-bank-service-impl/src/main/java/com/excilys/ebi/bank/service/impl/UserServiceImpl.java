package com.excilys.ebi.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.ebi.bank.dao.UserDao;
import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findByLoginFetchRoles(String login) {
		return userDao.findByLoginFetchRoles(login);
	}

}
