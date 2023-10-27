package es.alex.learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.alex.learning.classes.Usuarios;
import es.alex.learning.repos.UserRepository;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		System.out.println("Spring boot application to learn git, spring REST and SpringBoot!!");
		SpringApplication.run(LearningApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
						
			userRepository.save(new Usuarios("alex", "Bauer", "alex", "sa"));
			userRepository.save(new Usuarios("pepe", "O'Brian", "pepe", "sa"));

			// fetch all users
			System.out.println("Users found with findAll():");
			System.out.println("-------------------------------");
			userRepository.findAll().forEach(user -> {
				System.out.println(user.toString());
			});
			System.out.println("");

			// fetch an individual user by ID
			Usuarios user = userRepository.findById(1L);
			System.out.println("User found with findById(1L):");
			System.out.println("--------------------------------");
			System.out.println(user.toString());
			System.out.println("");

			// fetch users by login
			System.out.println("User found with findByLogin('alex'):");
			System.out.println("--------------------------------------------");
			userRepository.findByLogin("alex").forEach(bauer -> {
				System.out.println(bauer.toString());
			});
			System.out.println("");
			
		};
	}

}
