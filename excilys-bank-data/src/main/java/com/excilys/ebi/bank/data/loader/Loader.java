package com.excilys.ebi.bank.data.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Loader {

	private static final Logger LOGGER = LoggerFactory.getLogger(Loader.class);

	public static void main(String[] args) throws Exception {
		Loader loader = new Loader();
		loader.run();
	}

	private void run() {
		StopWatch sw = new StopWatch("Loader.run");
		sw.start("initDatabase");
		new ClassPathXmlApplicationContext("context/applicationContext.xml");
		sw.stop();

		LOGGER.info(sw.prettyPrint());
	}
}
