package com.excilys.ebi.bank.web.messages;

import java.util.List;

public interface Messages {

	boolean isEmpty();

	List<Message> getMessages();

	void add(String key, Object... args);

	void clear();
}
