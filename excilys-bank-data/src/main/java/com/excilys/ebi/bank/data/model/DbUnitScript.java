package com.excilys.ebi.bank.data.model;

import org.springframework.jdbc.datasource.init.DatabasePopulator;

import com.excilys.ebi.spring.dbunit.DbUnitDatabasePopulator;
import com.excilys.ebi.spring.dbunit.config.DataSetConfiguration;
import com.excilys.ebi.spring.dbunit.config.Phase;

public class DbUnitScript extends Script<DataSetConfiguration> {

	@Override
	public DatabasePopulator getPopulator() {
		DbUnitDatabasePopulator populator = new DbUnitDatabasePopulator();
		populator.setDataSetConfiguration(getScript());
		populator.setPhase(Phase.SETUP);
		return populator;
	}
}
