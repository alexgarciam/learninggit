package es.alex.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFirst {

	@Bean
	void getFirstConf() {
		System.out.println("This is the first configuration");
	}
	
}
