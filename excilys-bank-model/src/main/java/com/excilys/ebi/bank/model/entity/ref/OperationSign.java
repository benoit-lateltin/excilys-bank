package com.excilys.ebi.bank.model.entity.ref;

import java.math.BigDecimal;

public enum OperationSign {

	CREDIT, DEBIT;

	public static final BigDecimal ZERO = BigDecimal.valueOf(0);

	public static OperationSign getSign(BigDecimal amount) {
		return isCredit(amount) ? CREDIT : DEBIT;
	}

	public static boolean isCredit(BigDecimal amount) {
		return amount == null || amount.compareTo(ZERO) >= 0;
	}
}
