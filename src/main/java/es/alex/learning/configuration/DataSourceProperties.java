package es.alex.learning.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;


@Configuration
@Setter @Getter
public class DataSourceProperties {

	@Value("${test.hazelcast.datasource.mysql.url}")
	private String mysqlurl;
	
	@Value("${test.hazelcast.datasource.mysql.username}")
	private String mysqlusername;
	
	@Value("${test.hazelcast.datasource.mysql.password}")
	private String mysqlpassword;
	
	@Value("${test.hazelcast.datasource.mysql.driver-class-name}")
	private String mysqldriverclassname;
	
	@Value("${test.hazelcast.datasource.mysql.database-platform}")
	private String mysqldatabaseplatform;
	
	//-----------------------------------------------------------------
	
	@Value("${test.hazelcast.datasource.h2.url}")
	private String h2url;
	
	@Value("${test.hazelcast.datasource.h2.username}")
	private String h2username;
	
	@Value("${test.hazelcast.datasource.h2.password}")
	private String h2password;
	
	@Value("${test.hazelcast.datasource.h2.driver-class-name}")
	private String h2driverclassname;
	
	@Value("${test.hazelcast.datasource.h2.database-platform}")
	private String h2databaseplatform;
	
}
