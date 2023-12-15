package es.alex.learning;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.kubernetes.HazelcastKubernetesDiscoveryStrategyFactory;

import es.alex.learning.classes.mysql.Usuarios;
import es.alex.learning.repos.mysql.UserRepository;

@SpringBootApplication
public class LearningApplication {

	@Autowired
	BuildProperties buildProperties;

	public static void main(String[] args) {
		System.out.println("Spring boot application to learn git, spring REST and SpringBoot!!");

		SpringApplication.run(LearningApplication.class, args);

	}

	@Bean(name = "hazelcastInstance")
	public HazelcastInstance hazelcastInstance() throws IOException {

		// ------------------------------
		// hazelcast

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("			Starting Hazelcast Server 					 ");
		System.out.println("---------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();

		Config helloWorldConfig = new Config();
		helloWorldConfig.setClusterName("hello-world");
	
		//servicio dns		
		helloWorldConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
		helloWorldConfig.getNetworkConfig().getJoin().getKubernetesConfig().setEnabled(true)
	      .setProperty("service-dns", "hazelcast-service");
		
		
		HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);

		Map<String, String> map = hz.getMap("my-distributed-map");
		map.put("1", "John");
		map.put("2", "Mary");
		map.put("3", "Jane");

		System.out.println(map.get("1"));
		System.out.println(map.get("2"));
		System.out.println(map.get("3"));

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("			Hazelcast Server Started 					 ");
		System.out.println("---------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();
		return hz;
	}

	@Bean(name = "HazelcastInstanceClient")
	public HazelcastInstance HazelcastInstanceClient() throws IOException {
		// ------------------------------
		// hazelcast client
		HazelcastInstance client;

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("			Starting Hazelcast Client 2.0				 ");
		System.out.println("---------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setClusterName("hello-world");
		//clientConfig.getNetworkConfig().getKubernetesConfig().setEnabled(true).setProperty("service-dns", "hazelcast-service");
		clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701", "127.0.0.1:5702", "127.0.0.1:5703");
		client = HazelcastClient.newHazelcastClient(clientConfig);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("			Hazelcast Client Started 					 ");
		System.out.println("---------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("			deployed version: " + buildProperties.getVersion());
		System.out.println("------------------------------------------------------------------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();

		return client;
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
