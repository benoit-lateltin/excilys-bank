package com.excilys.ebi.bank.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.UserDao;
import com.excilys.ebi.bank.model.entity.QUser;
import com.excilys.ebi.bank.model.entity.User;

@Repository
public class UserDaoImpl extends QueryDslRepositorySupport implements UserDao {

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public User findByLoginFetchRoles(String login) {

		QUser user = QUser.user;
		return from(user).where(user.login.eq(login)).innerJoin(user.roles).fetch().uniqueResult(user);
	}
}
