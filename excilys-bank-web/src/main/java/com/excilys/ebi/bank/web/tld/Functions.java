package com.excilys.ebi.bank.web.tld;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Functions {

	public static String url(String url) {
		// don't touch absolute URLs
		if (UrlUtils.isAbsoluteUrl(url))
			return url;

		// normalize relative URLs against a context root
		if (url.startsWith("/"))
			return (ctx() + url);
		else
			return url;
	}

	public static String ctx() {

		HttpServletRequest request = ServletRequestAttributes.class.cast(RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getContextPath();
	}

	public static String amount(BigDecimal amount) {

		StringBuilder buf = new StringBuilder();
		buf.append("<span class=\"figure ").append(amount.compareTo(BigDecimal.valueOf(0.0)) >= 0 ? "green" : "red").append("\">").append(amount).append("</span>");
		return buf.toString();
	}

	public static String ifThenElse(boolean when, String then, String otherwise) {
		return when ? then : otherwise;
	}
}
