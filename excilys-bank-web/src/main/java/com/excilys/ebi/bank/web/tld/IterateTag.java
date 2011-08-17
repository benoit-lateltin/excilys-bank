package com.excilys.ebi.bank.web.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IterateTag extends SimpleTagSupport {

	private int count = 0;

	public void setCount(int value) {
		this.count = value;
	}

	public void doTag() throws JspException, IOException {
		for (int i = 0; i < count; i++)
			getJspBody().invoke(null);
	}
}
