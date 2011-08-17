package com.excilys.ebi.bank.model.entity.ref;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Ref implements Serializable {

	private static final long serialVersionUID = -7525352878822322301L;

	private String name;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
