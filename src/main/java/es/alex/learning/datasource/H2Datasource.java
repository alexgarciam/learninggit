package es.alex.learning.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import es.alex.learning.configuration.DataSourceProperties;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "h2entityManagerFactory", 
		transactionManagerRef = "h2transactionManager", 
		basePackages = {"es.alex.learning.repos.h2", }
		)
public class H2Datasource {

	@Autowired
	private  DataSourceProperties dataSourceProperties;
				
	
	@Bean(name="h2datasource_")
	public DataSource h2Datasource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dataSourceProperties.getH2driverclassname());
        dataSourceBuilder.url(dataSourceProperties.getH2url());
        dataSourceBuilder.username(dataSourceProperties.getH2username());
        dataSourceBuilder.password(dataSourceProperties.getH2password());
        return dataSourceBuilder.build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean h2entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(h2Datasource());
		em.setPackagesToScan("es.alex.learning.classes.h2");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager h2transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(h2entityManagerFactory().getObject());

		return transactionManager;
	}

	
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		return properties;
	}
	
}
