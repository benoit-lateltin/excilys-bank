package com.excilys.ebi.bank.data.model;

import org.springframework.jdbc.datasource.init.DatabasePopulator;

public abstract class Script<T> {

	private T script;

	private String name;

	public abstract DatabasePopulator getPopulator();

	public T getScript() {
		return script;
	}

	public String getName() {
		return name;
	}

	public void setScript(T script) {
		this.script = script;
	}

	public void setName(String name) {
		this.name = name;
	}
}
