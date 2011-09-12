package com.excilys.ebi.bank.data.loader;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.util.StopWatch;

import com.excilys.ebi.bank.data.model.Script;

public class Loader {

	private static final Logger LOGGER = LoggerFactory.getLogger(Loader.class);

	public static void main(String[] args) throws Exception {
		Loader loader = new Loader();
		loader.run();
	}

	private void run() throws Exception {
		StopWatch sw = new StopWatch("initDatabase");

		ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
		@SuppressWarnings("rawtypes")
		Map<String, Script> scripts = context.getBeansOfType(Script.class);
		DataSource dataSource = context.getBean(DataSource.class);

		for (Script<?> script : scripts.values()) {
			sw.start("loading script " + script.getName());
			DataSourceInitializer initializer = new DataSourceInitializer();
			initializer.setDataSource(dataSource);
			initializer.setDatabasePopulator(script.getPopulator());
			initializer.afterPropertiesSet();
			sw.stop();
		}

		LOGGER.info(sw.prettyPrint());
	}
}
