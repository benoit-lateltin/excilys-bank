package com.excilys.ebi.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.ebi.bank.model.entity.Operation;

public interface OperationDao extends JpaRepository<Operation, Integer>, OperationDaoCustom {
}
