package com.excilys.ebi.bank.hibernate;

import java.util.Map;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class ExtendedHibernateJpaVendorAdapter extends HibernateJpaVendorAdapter {

	private Map<String, Object> jpaPropertyMap;

	@Override
	public Map<String, Object> getJpaPropertyMap() {
		return jpaPropertyMap;
	}

	public void setJpaPropertyMap(Map<String, Object> jpaPropertyMap) {
		this.jpaPropertyMap = jpaPropertyMap;
	}
}
