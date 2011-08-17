package com.excilys.ebi.bank.web.controller;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultsController {

	@RequestMapping("/private/bank/account/*/operations.html")
	public String displayOperationsWithDefaults() {

		DateTime now = new DateTime();

		return new StringBuilder().append("forward:year/").append(now.getYear()).append("/month/").append(now.getMonthOfYear()).append("/operations.html").toString();
	}

	@RequestMapping("/private/bank/account/*/cards/*/operations.html")
	public String displayCardOperationsWithDefaults() {

		DateTime now = new DateTime();

		return new StringBuilder().append("forward:year/").append(now.getYear()).append("/month/").append(now.getMonthOfYear()).append("/operations.html").toString();
	}
}
