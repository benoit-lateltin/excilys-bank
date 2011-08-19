package com.excilys.ebi.bank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/admin")
@WebPageModelAttribute(WebPage.ADMIN)
public class AdminController {

	@RequestMapping("/admin.html")
	public void admin() {
	}
}
