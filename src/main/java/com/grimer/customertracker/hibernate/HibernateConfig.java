package com.grimer.customertracker.hibernate;

import javax.sql.DataSource;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.hibernate.SessionFactory;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@ComponentScans(value = { @ComponentScan("com.grimer.customertracker.dao"),
	      				  @ComponentScan("com.grimer.customertracker.service") })
public class HibernateConfig {
 
	@Autowired
	private Environment env;
 
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
		dataSource.setUrl(env.getRequiredProperty("datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("datasource.password"));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.grimer.customertracker.entity" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	 
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("hibernate.batch.size"));
		properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hibernate.current.session.context.class"));
		
	      // Setting C3P0 properties
	    properties.put(AvailableSettings.C3P0_MIN_SIZE, 
	            env.getProperty("hibernate.c3p0.min_size"));
	    properties.put(AvailableSettings.C3P0_MAX_SIZE, 
	            env.getProperty("hibernate.c3p0.max_size"));
	    properties.put(AvailableSettings.C3P0_ACQUIRE_INCREMENT,
	            env.getProperty("hibernate.c3p0.acquire_increment"));
	    properties.put(AvailableSettings.C3P0_TIMEOUT, 
	            env.getProperty("hibernate.c3p0.timeout"));
	    properties.put(AvailableSettings.C3P0_MAX_STATEMENTS, 
	            env.getProperty("hibernate.c3p0.max_statements"));
		
		return properties;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
