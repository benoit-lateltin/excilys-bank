package com.excilys.ebi.bank.data.context;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CloudApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		CloudEnvironment env = new CloudEnvironment();
		if (env.getInstanceInfo() != null) {
			applicationContext.getEnvironment().setActiveProfiles("cloudfoundry");
		} else {
			applicationContext.getEnvironment().setActiveProfiles("default");
		}
	}
}