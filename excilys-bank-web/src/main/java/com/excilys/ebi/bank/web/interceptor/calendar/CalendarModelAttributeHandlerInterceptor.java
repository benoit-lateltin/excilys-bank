package com.excilys.ebi.bank.web.interceptor.calendar;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.AnnotatedMethodHandlerInterceptor;

public class CalendarModelAttributeHandlerInterceptor extends AnnotatedMethodHandlerInterceptor<CalendarModelAttribute> {

	@Autowired
	protected BankService bankService;

	public CalendarModelAttributeHandlerInterceptor() {
		super(CalendarModelAttribute.class);
	}

	@Override
	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, ModelAndView modelAndView) throws Exception {
		exportCalendar(modelAndView.getModelMap());
	}

	private void exportCalendar(ModelMap model) {
		Calendar calendar = new Calendar();

		// build months
		List<DateTime> months = calendar.getMonths();

		DateMidnight thisMonth = new DateMidnight().withDayOfMonth(1);
		months.add(thisMonth.toDateTime());

		// display last 6 months
		while (months.size() < 6) {
			thisMonth = thisMonth.minusMonths(1);
			months.add(thisMonth.toDateTime());
		}

		Collections.reverse(months);

		// build selectedMonth
		Integer year = Integer.class.cast(model.get("year"));
		Integer month = Integer.class.cast(model.get("month"));
		if (year != null) {
			Assert.notNull(month, "month is required id year is specified");
			DateTime selectedMonth = new DateMidnight().withDayOfMonth(1).withYear(year).withMonthOfYear(month).toDateTime();
			calendar.setSelectedMonth(selectedMonth);
		}

		model.addAttribute("calendar", calendar);
	}
}
