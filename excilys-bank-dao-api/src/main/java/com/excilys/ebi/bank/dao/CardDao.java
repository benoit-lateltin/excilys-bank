package com.excilys.ebi.bank.dao;

import java.util.List;

import com.excilys.ebi.bank.model.entity.Card;

public interface CardDao {

	List<Card> findByAccountNumber(String accountNumber);
}
