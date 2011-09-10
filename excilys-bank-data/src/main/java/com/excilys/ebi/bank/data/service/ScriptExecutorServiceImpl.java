package com.excilys.ebi.bank.data.service;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excilys.ebi.bank.data.model.Script;

@Service
public class ScriptExecutorServiceImpl implements ScriptExecutorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptExecutorServiceImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	@Async
	public void execute(Script<?> script) throws Exception {

		LOGGER.info("start executing script {}", script.getName());

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(script.getPopulator());
		initializer.afterPropertiesSet();

		LOGGER.info("done executing script {}", script.getName());
	}
}
