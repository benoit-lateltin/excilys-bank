package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferCommand implements Serializable {

	private static final long serialVersionUID = -1733253994577062650L;

	@NotNull
	private String debitedAccountNumber;

	@NotNull
	private String creditedAccountNumber;

	@Min(10)
	private BigDecimal amount;

	public String getDebitedAccountNumber() {
		return debitedAccountNumber;
	}

	public String getCreditedAccountNumber() {
		return creditedAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setDebitedAccountNumber(String debitedAccountNumber) {
		this.debitedAccountNumber = debitedAccountNumber;
	}

	public void setCreditedAccountNumber(String creditedAccountNumber) {
		this.creditedAccountNumber = creditedAccountNumber;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
