package com.excilys.ebi.bank.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.excilys.ebi.utils.spring.log.slf4j.InjectLogger;

public class LoggingMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@InjectLogger
	private Logger logger;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		logger.error(ex.getMessage(), ex);
		return super.doResolveException(request, response, handler, ex);
	}
}
