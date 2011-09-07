package com.excilys.ebi.bank.model;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

public class YearMonth {

	private final int year;

	private final int monthOfYear;

	public YearMonth(int year, int monthOfYear) {
		this.year = year;
		this.monthOfYear = monthOfYear;
	}

	public int getYear() {
		return year;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public int getValue() {
		return year * 100 + monthOfYear;
	}

	public Range<DateTime> getRange() {
		DateMidnight start = new DateMidnight().withYear(year).withMonthOfYear(monthOfYear);
		DateMidnight end = start.plusMonths(1);
		return new Range<DateTime>(start.toDateTime(), end.toDateTime());
	}
}
