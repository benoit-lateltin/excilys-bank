package com.excilys.ebi.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.ebi.bank.model.entity.ref.OperationType;
import com.excilys.ebi.bank.model.entity.ref.OperationTypeRef;

public interface OperationTypeRefDao extends JpaRepository<OperationTypeRef, OperationType> {

}
