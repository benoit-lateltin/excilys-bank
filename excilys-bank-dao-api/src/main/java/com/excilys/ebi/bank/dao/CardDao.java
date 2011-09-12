package com.excilys.ebi.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.ebi.bank.model.entity.Card;

public interface CardDao extends JpaRepository<Card, Integer> {

	Card findByNumber(String number);

	List<Card> findByAccountId(Integer accountId);
}
