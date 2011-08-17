package com.excilys.ebi.bank.web.messages;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -4690200558095237185L;

	private final String key;
	private final Object[] args;

	public Message(String key, Object... args) {
		this.key = key;
		this.args = args;
	}

	public String getKey() {
		return key;
	}

	public Object[] getArgs() {
		return args;
	}
}
