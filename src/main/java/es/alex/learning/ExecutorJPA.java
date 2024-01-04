package es.alex.learning;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import es.alex.learning.classes.mysql.Usuarios;
import es.alex.learning.repos.mysql.UserRepository;

@Component
public class ExecutorJPA implements CommandLineRunner {

			
	//JPA
	@Autowired
	UserRepository userRepository;

	@Autowired
	ClientConfig clientConfig;  
	
	@Override
	public void run(String... args) throws Exception {
				
		System.out.println("--------------------------------------------------------------");
		System.out.println("Spring JPA");


		userRepository.save(new Usuarios("alex1", "Bauer", "alex", "sa"));
		userRepository.save(new Usuarios("alex12", "O'Brian", "pepe", "sa"));
		userRepository.save(new Usuarios("alex13", "O'Brian", "pepe", "sa"));
		userRepository.save(new Usuarios("alex14", "O'Brian", "pepe", "sa"));

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
		
		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println("--------------------------------------------");

		System.out.println("TESTING HAZELCAST CLIENT");
		System.out.println("--------------------------------------------");
		
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		

		Map<String, String> map = client.getMap("my-distributed-map");
		

		System.out.println(map.get("1"));
		System.out.println(map.get("2"));
		System.out.println(map.get("3"));
		
	}

}
