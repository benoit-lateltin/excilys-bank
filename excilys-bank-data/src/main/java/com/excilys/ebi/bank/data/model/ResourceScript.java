package com.excilys.ebi.bank.data.model;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class ResourceScript extends Script<Resource> {

	@Override
	public DatabasePopulator getPopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(getScript());
		return populator;
	}
}
