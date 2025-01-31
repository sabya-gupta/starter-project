
package com.maybank.labs.starter.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * The Class PersistenceJPAConfig.
 */

public abstract class PersistenceJPAConfig {

	/** The db instance. */

	protected String dbInstance;

	/** The packages to scan. */

	protected String packagesToScan;

	/**
	 * Sets the db instance.
	 *
	 * @param dbInstance the new db instance
	 */

	public void setDbInstance(final String dbInstance) {
		this.dbInstance = dbInstance;
	}

	/**
	 * Sets the packages to scan.
	 *
	 * @param packagesToScan the new packages to scan
	 */

	public void setPackagesToScan(final String packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	/**
	 * Entity manager factory.
	 *
	 * @return the local container entity manager factory bean
	 */

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(packagesToScan);
		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	/**
	 * Data source.
	 *
	 * @return the data source
	 */

	@Bean(destroyMethod = "")
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		return dsLookup.getDataSource(dbInstance);
	}

	/**
	 * Transaction manager.
	 *
	 * @param emf the emf
	 * @return the platform transaction manager
	 */

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	/**
	 * Exception translation.
	 *
	 * @return the persistence exception translation post processor
	 */

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * Additional properties.
	 *
	 * @return the properties
	 */
	Properties additionalProperties() {
		System.out.println("--------------------SETTING HIBERNATE PROPS.......................");
		final Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.SQL", "DEBUG");
		properties.setProperty("hibernate.type", "TRACE");
		properties.setProperty("org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");//For auditing the tables using Hibernate Envers
		return properties;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
