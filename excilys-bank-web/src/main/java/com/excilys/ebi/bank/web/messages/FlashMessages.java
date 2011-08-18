package com.excilys.ebi.bank.web.messages;

public interface FlashMessages {

	boolean isEmpty();

	void add(String key, Object... args);

	void clear();
}
