package com.maybank.labs.starter.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextProvider.applicationContext = applicationContext;// NOSONAR - This is
		// recommended by Spring
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
