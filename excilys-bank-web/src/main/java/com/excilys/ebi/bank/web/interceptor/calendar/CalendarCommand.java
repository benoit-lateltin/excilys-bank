package com.excilys.ebi.bank.web.interceptor.calendar;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class CalendarCommand implements Serializable {

	private static final long serialVersionUID = 1870354008317144931L;

	private Integer month;

	private Integer year;

	private DateTime selectedMonth;

	private List<DateTime> months = newArrayList();

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}

	public DateTime getSelectedMonth() {
		return selectedMonth;
	}

	public List<DateTime> getMonths() {
		return months;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setSelectedMonth(DateTime selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public void setMonths(List<DateTime> months) {
		this.months = months;
	}
}
