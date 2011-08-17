package com.excilys.ebi.bank.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.CardDao;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.QAccount;
import com.excilys.ebi.bank.model.entity.QCard;

@Repository
public class CardDaoImpl extends QueryDslRepositorySupport implements CardDao {

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<Card> findByAccountNumber(String accountNumber) {

		QCard card = QCard.card;
		QAccount account = QAccount.account;

		return from(card).innerJoin(card.account, account).where(account.number.eq(accountNumber)).list(card);
	}
}
