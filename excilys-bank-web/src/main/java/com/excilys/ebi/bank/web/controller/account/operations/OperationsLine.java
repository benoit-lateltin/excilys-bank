package com.excilys.ebi.bank.web.controller.account.operations;

import java.math.BigDecimal;

import com.excilys.ebi.bank.model.entity.ref.OperationStatus;

public class OperationsLine {

	private BigDecimal amount;
	private String name;
	private String date;
	private OperationStatus status;

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
