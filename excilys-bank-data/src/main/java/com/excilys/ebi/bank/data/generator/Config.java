package com.excilys.ebi.bank.data.generator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Config {
	private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	public static final Date MIN_OPERATION_DATE;
	public static final Date MAX_OPERATION_DATE;
	public static final Date MIN_ACCOUNT_BALANCE_DATE;
	public static final Date MAX_ACCOUNT_BALANCE_DATE;
	public static final Date MIN_CARD_PENDING_DATE;
	public static final Date MAX_CARD_PENDING_DATE;
	public static final long PENDING_WEIGHT = 80; // en %
	public static final long CARD_OPERATION_WEIGHT = 75; // en %
	public static final long MIN_CARD_OPERATION_ID = 10000000;

	static {
		try {
			MIN_OPERATION_DATE = FORMATTER.parse("2011-02-01");
			MAX_OPERATION_DATE = FORMATTER.parse("2011-09-02");
			MIN_ACCOUNT_BALANCE_DATE = FORMATTER.parse("2011-09-01");
			MAX_ACCOUNT_BALANCE_DATE = FORMATTER.parse("2011-09-02");
			MIN_CARD_PENDING_DATE = FORMATTER.parse("2011-09-01");
			MAX_CARD_PENDING_DATE = FORMATTER.parse("2011-09-02");
		} catch (ParseException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
