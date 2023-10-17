package es.alex.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		System.out.println("Spring boot application");
		SpringApplication.run(LearningApplication.class, args);
	}

}
