package com.excilys.ebi.bank.model.entity.ref;

import java.math.BigDecimal;

public enum OperationSign {

	CREDIT, DEBIT;

	public static OperationSign getSign(BigDecimal amount) {
		return isCredit(amount) ? CREDIT : DEBIT;
	}

	public static boolean isCredit(BigDecimal amount) {
		return amount == null || amount.compareTo(BigDecimal.ZERO) >= 0;
	}
}
