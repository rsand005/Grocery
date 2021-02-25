package com.DailyGroceryShop.config;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.function.ServerResponse.Context;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.DailyGroceryShop.dao.ProdInventoryDao;
import com.DailyGroceryShop.dao.ProdInventoryDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig {
	
	@Bean
	public ProdInventoryDao prodInventoryDao() {
		return new ProdInventoryDaoImpl();
	}
	

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setViewClass(JstlView.class);
		return internalResourceViewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("/WEB-INF/messages/message");
		return messageSource;
	}

	/*
	 * @Bean public DataSource getDataSource() { DriverManagerDataSource dataSource
	 * = new DriverManagerDataSource();
	 * dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	 * dataSource.setUrl("jdbc:oracle:thin:@//localhost:1521/xe");
	 * dataSource.setUsername("shop"); dataSource.setPassword("root");
	 * 
	 * return dataSource; }
	 * 
	 * public Properties hibernateProperties() { Properties properties = new
	 * Properties(); properties.put("hibernate.dialect",
	 * "org.hibernate.dialect.Oracle10gDialect");
	 * properties.put("hibernate.hbm2ddl.auto", "update");
	 * properties.put("show_sql", "true"); return properties;
	 * 
	 * }
	 * 
	 * // JPA Repository
	 * 
	 * @Primary
	 * 
	 * @Bean(name = "entityManagerFactory") public
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
	 * LocalContainerEntityManagerFactoryBean();
	 * entityManagerFactoryBean.setDataSource(getDataSource());
	 * entityManagerFactoryBean.setPackagesToScan("com.DailyGroceryShop.domain");
	 * entityManagerFactoryBean.setJpaProperties(hibernateProperties());
	 * entityManagerFactoryBean.setJpaVendorAdapter(new
	 * HibernateJpaVendorAdapter());
	 * 
	 * return entityManagerFactoryBean;
	 * 
	 * }
	 * 
	 * @Bean public JdbcTemplate jdbcTemplate() { JdbcTemplate JdbcTemplate = new
	 * JdbcTemplate(); JdbcTemplate.setDataSource(getDataSource()); return
	 * JdbcTemplate; }
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/dailygroceryshop?useSSL=false&allowPublicKeyReterival=false");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("show_sql", "true");
		properties.put("spring.jpa.properties.hibernate.jdbc.batch_size", "5");
		properties.put("hibernate.order_updates", "true");
		properties.put("hibernate.batch_versioned_data", "true");
		
		return properties;

	}

	// JPA Repository
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("com.DailyGroceryShop.domain");
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		return entityManagerFactoryBean;

	}
	

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate JdbcTemplate = new JdbcTemplate();
		JdbcTemplate.setDataSource(dataSource());
		return JdbcTemplate;
	}

	//For Hibernate session
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.DailyGroceryShop.domain");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	/*
	 * @Bean public HibernateTransactionManager transactionManager() {
	 * HibernateTransactionManager txManager = new HibernateTransactionManager();
	 * txManager.setSessionFactory(sessionFactory().getObject()); return txManager;
	 * }
	 */
	
	
	
}
