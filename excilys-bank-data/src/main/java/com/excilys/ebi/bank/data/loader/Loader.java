package com.excilys.ebi.bank.data.loader;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

import com.excilys.ebi.spring.dbunit.DefaultDataLoader;
import com.excilys.ebi.spring.dbunit.config.DataSetConfiguration;
import com.excilys.ebi.spring.dbunit.config.Phase;

public class Loader {

	private final ApplicationContext context;
	private final SimpleJdbcTemplate jdbcTemplate;
	private final DataSetConfiguration dataSetConfiguration;
	private List<Resource> beforeLoadScripts;
	private List<Resource> afterLoadScripts;

	public static void main(String[] args) throws Exception {
		Loader loader = new Loader();
		loader.run();
	}

	private Loader() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = context.getBean(SimpleJdbcTemplate.class);
		dataSetConfiguration = context.getBean(DataSetConfiguration.class);
		beforeLoadScripts = newArrayList();
		afterLoadScripts = newArrayList();

		PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		try {
			for (Resource resource : resourceResolver.getResources("classpath*:ddl/before-*.sql"))
				beforeLoadScripts.add(resource);

			for (Resource resource : resourceResolver.getResources("classpath*:ddl/after-*.sql"))
				afterLoadScripts.add(resource);

		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	private void run() throws Exception {
		executeBeforeLoadScripts();
		try {
			loadData();
		} catch (BatchUpdateException e) {
			e.printStackTrace();
			throw e.getNextException();
		}
		executeAfterLoadScripts();
	}

	private void executeBeforeLoadScripts() {

		for (Resource resource : beforeLoadScripts)
			SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate, resource, false);
	}

	private void loadData() throws Exception {
		new DefaultDataLoader().execute(context, dataSetConfiguration, Phase.SETUP);
	}

	private void executeAfterLoadScripts() {
		for (Resource resource : afterLoadScripts)
			SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate, resource, false);
	}
}
