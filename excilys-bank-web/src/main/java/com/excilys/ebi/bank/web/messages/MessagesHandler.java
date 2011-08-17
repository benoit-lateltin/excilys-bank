package com.excilys.ebi.bank.web.messages;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component("messages")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class MessagesHandler implements Messages, Serializable {

	private static final long serialVersionUID = -5602333916735909279L;

	private final List<Message> messages = newArrayList();

	@Override
	public boolean isEmpty() {
		return messages.isEmpty();
	}

	@Override
	public List<Message> getMessages() {
		return ImmutableList.copyOf(messages);
	}

	@Override
	public void add(String key, Object... args) {
		messages.add(new Message(key, args));
	}

	@Override
	public void clear() {
		messages.clear();
	}
}
