package es.alex.learning.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
public class CustomWebSecurity {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			
		
			.authorizeHttpRequests((requests) -> requests
                // another matchers
                .requestMatchers(toH2Console()).permitAll() // <-
                // another matchers
            )
			.authorizeHttpRequests((authorize) -> authorize
					.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
            .csrf((protection) -> protection
                .ignoringRequestMatchers(toH2Console()) // <- 
            )
            .headers((header) -> header
                .frameOptions().sameOrigin()
            );

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("alex")
			.password("alex")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
}