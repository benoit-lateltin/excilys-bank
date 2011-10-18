package com.excilys.ebi.bank.model;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

public class YearMonth {

	private final int year;

	private final int monthOfYear;

	private final Range<DateTime> range;

	public YearMonth(int year, int monthOfYear) {
		this.year = year;
		this.monthOfYear = monthOfYear;

		DateMidnight start = new DateMidnight().withYear(year).withMonthOfYear(monthOfYear).withDayOfMonth(1);
		DateMidnight end = start.plusMonths(1);
		range = new Range<DateTime>(start.toDateTime(), end.toDateTime());
	}

	public int getYear() {
		return year;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public Range<DateTime> getRange() {
		return range;
	}
}
