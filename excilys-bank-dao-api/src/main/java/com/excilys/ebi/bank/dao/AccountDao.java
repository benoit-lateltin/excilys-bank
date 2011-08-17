package com.excilys.ebi.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.User;

public interface AccountDao extends JpaRepository<Account, Integer>, AccountDaoCustom {

	List<Account> findByUsers(User user);
}
