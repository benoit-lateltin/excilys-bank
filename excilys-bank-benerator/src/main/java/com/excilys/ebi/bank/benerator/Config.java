package com.excilys.ebi.bank.benerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Config {
	private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public static Date minDate;
	public static Date maxDate;
	public static Date accountMinDate;
	public static Date accountMaxDate;
	public static Date cardPendingMinDate;
	public static final long pendingWeight = 80; //en %
	public static final long cardOperationWeight = 75; //en %

	static {
		try {
			minDate = formatter.parse("2011-01-01");
			maxDate = formatter.parse("2011-08-20");
			accountMinDate = formatter.parse("2011-08-01");
			accountMaxDate = formatter.parse("2011-08-20");
			cardPendingMinDate = formatter.parse("2011-08-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
