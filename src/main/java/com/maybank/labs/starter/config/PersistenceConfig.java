
package com.maybank.labs.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

@EnableTransactionManagement
public class PersistenceConfig extends PersistenceJPAConfig {

	public PersistenceConfig() {
		setDbInstance("jdbc/CCUIDB");
		setPackagesToScan("com.maybank");
	}

}
