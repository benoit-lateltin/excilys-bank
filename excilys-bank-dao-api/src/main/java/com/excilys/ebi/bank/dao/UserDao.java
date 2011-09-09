package com.excilys.ebi.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.ebi.bank.model.entity.User;

public interface UserDao extends JpaRepository<User, Integer>, UserDaoCustom {

}
