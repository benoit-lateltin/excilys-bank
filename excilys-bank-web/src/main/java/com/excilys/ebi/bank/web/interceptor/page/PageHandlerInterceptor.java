package com.excilys.ebi.bank.web.interceptor.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PageHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if (handler instanceof HandlerMethod && HandlerMethod.class.cast(handler).getBean() instanceof PageController) {
			modelAndView.addObject(Page.PAGE_MODEL, PageController.class.cast(HandlerMethod.class.cast(handler).getBean()).getPage());
		}
	}
}
