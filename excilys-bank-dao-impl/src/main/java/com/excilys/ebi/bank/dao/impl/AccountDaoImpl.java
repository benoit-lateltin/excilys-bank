package com.excilys.ebi.bank.dao.impl;

import static com.excilys.ebi.bank.model.entity.QAccount.account;
import static com.excilys.ebi.bank.model.entity.QUser.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.AccountDaoCustom;
import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.User;

@Repository
public class AccountDaoImpl extends QueryDslRepositorySupport implements AccountDaoCustom {

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<Account> findByUserFetchCards(User user) {

		return from(account).where(account.users.contains(user)).leftJoin(account.cards).fetch().listDistinct(account);
	}

	@Override
	public Account findByNumberFetchCards(String accountNumber) {

		return from(account).where(account.number.eq(accountNumber)).leftJoin(account.cards).fetch().uniqueResult(account);
	}

	@Override
	public long countAccountsByIdAndUserLogin(Integer id, String login) {
		return from(account).innerJoin(account.users, user).where(account.id.eq(id), user.login.eq(login)).countDistinct();
	}
}
