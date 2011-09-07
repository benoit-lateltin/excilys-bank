package com.excilys.ebi.bank.model;

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
}
