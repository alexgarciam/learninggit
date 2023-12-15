package es.alex.learning.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import es.alex.learning.configuration.DataSourceProperties;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "mysqlentityManagerFactory", 
		transactionManagerRef = "mysqltransactionManager", 
		basePackages = {"es.alex.learning.repos.mysql", }
		)
public class MYSQLDatasource {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Bean
	@Primary
	public DataSource mysqlDatasource() {
		
		//creación del DS con Hikari
		HikariConfig config = new HikariConfig();
		HikariDataSource ds;
		
		config.setJdbcUrl( dataSourceProperties.getMysqlurl() );
        config.setUsername( dataSourceProperties.getMysqlusername() );
        config.setPassword( dataSourceProperties.getMysqlpassword() );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
		
        //Creación con Spring sin hikari
        /*
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(dataSourceProperties.getMysqldriverclassname());
		dataSourceBuilder.url(dataSourceProperties.getMysqlurl());
		dataSourceBuilder.username(dataSourceProperties.getMysqlusername());
		dataSourceBuilder.password(dataSourceProperties.getMysqlpassword());
		return dataSourceBuilder.build();
		*/
		
		return ds;
	}
	
		
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean mysqlentityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysqlDatasource());
		em.setPackagesToScan("es.alex.learning.classes.mysql");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager mysqltransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mysqlentityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		return properties;
	}

	/*
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(mysqlDatasource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		return dataSourceInitializer;
	}
*/

}