package com.excilys.ebi.bank.web.messages;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.excilys.ebi.utils.web.flash.FlashScope;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class FlashMessagesHandler implements FlashMessages, Serializable {

	private static final long serialVersionUID = -5602333916735909279L;

	private final List<Message> messages = newArrayList();

	@PostConstruct
	public void init() {
		FlashScope.bind("messages").to(messages);
	}

	@Override
	public boolean isEmpty() {
		return messages.isEmpty();
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
