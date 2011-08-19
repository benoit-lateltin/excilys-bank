package com.excilys.ebi.bank.web.interceptor.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.AnnotatedMethodHandlerInterceptor;

public class WebPageModelAttributeHandlerInterceptor extends AnnotatedMethodHandlerInterceptor<WebPageModelAttribute> {

	@Autowired
	protected BankService bankService;

	public WebPageModelAttributeHandlerInterceptor() {
		super(WebPageModelAttribute.class);
	}

	@Override
	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null)
			modelAndView.addObject(WebPage.PAGE_MODEL, getAnnotation(handlerMethod).value());
	}
}
