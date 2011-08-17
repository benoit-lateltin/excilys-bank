package com.excilys.ebi.bank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.web.interceptor.page.Page;
import com.excilys.ebi.bank.web.interceptor.page.PageController;

@Controller
@RequestMapping("/private/admin")
public class AdminController implements PageController {

	@RequestMapping("/admin.html")
	public void admin() {
	}

	@Override
	public Page getPage() {
		return Page.ADMIN;
	}
}
