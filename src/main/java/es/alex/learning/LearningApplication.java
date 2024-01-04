package es.alex.learning;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import es.alex.learning.classes.mysql.Telefonos;
import es.alex.learning.configuration.DataSourceProperties;
import es.alex.learning.repos.mysql.TelefonoRepository;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication(exclude = HazelcastAutoConfiguration.class)
@Log4j2
public class LearningApplication {

	@Autowired
	BuildProperties buildProperties;

	@Autowired
	private DataSourceProperties dataSourceProperties;

	public static void main(String[] args) {
		System.out.println("Spring boot application to learn git, spring REST and SpringBoot!!");
		String profile_active = System.getenv("SPRING_PROFILES_ACTIVE");
		System.out.println("active profile: " + profile_active);

		SpringApplication.run(LearningApplication.class, args);

	}

	@Bean(name = "hazelcastInstance")
	public HazelcastInstance hazelcastInstance() throws IOException {

		System.out.println("Changing value: " + dataSourceProperties.getChangingvalue());
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
		helloWorldConfig.setClusterName("dev");

		String environment = dataSourceProperties.getEnvironment();
		if (!environment.equals("local")) {
			log.debug("connecting hazelcasst server to cluster kubernetes environment");
			// servicio dns
			helloWorldConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
			helloWorldConfig.getNetworkConfig().getJoin().getKubernetesConfig().setEnabled(true)
					.setProperty("service-dns", "hazelcast-service");

		} else {
			log.debug("connecting hazelcasst server to cluster local environment");
		}

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

	/*
	@Bean
	public ClientConfig clientConfig() throws IOException {
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
		clientConfig.setClusterName("dev");
		clientConfig.getTpcConfig().setEnabled(false);

		String environment = dataSourceProperties.getEnvironment();

		if (!environment.equals("local")) {
			log.debug("connecting hazelcasst server to cluster kubernetes environment");
			clientConfig.getNetworkConfig().getCloudConfig().setEnabled(false);
			clientConfig.getNetworkConfig().getAutoDetectionConfig().setEnabled(false);
			clientConfig.getNetworkConfig().getKubernetesConfig().setEnabled(true);
			clientConfig.getNetworkConfig().getKubernetesConfig().setEnabled(true).setProperty("namespace", "default")
					.setProperty("service-dns", "hazelcast-service");
		} else {
			log.debug("connecting hazelcasst server to cluster local environment");
			clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701",
					 "127.0.0.1:5702", "127.0.0.1:5703");
		}
		
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

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("			deployed version: " + buildProperties.getVersion());
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");

		System.out.println();
		System.out.println();
		System.out.println();

		return clientConfig;
	}
*/
	
	@Bean
	public CommandLineRunner demo(TelefonoRepository telefonoRepository) {
		return (args) -> {

			telefonoRepository.save(new Telefonos("Redmi 12", "Redmi"));
			telefonoRepository.save(new Telefonos("Redmi 11", "Redmi"));
			telefonoRepository.save(new Telefonos("iphone 13", "iphone"));

			// fetch all users
			System.out.println("telefonoss found with findAll():");
			System.out.println("-------------------------------");
			telefonoRepository.findAll().forEach(user -> {
				System.out.println(user.toString());
			});
			System.out.println("");

			// fetch an individual user by ID
			Telefonos telefono = telefonoRepository.findById(1L);
			System.out.println("telefonoss found with findById(1L):");
			System.out.println("--------------------------------");
			System.out.println(telefono.toString());
			System.out.println("");

			// fetch users by login
			System.out.println("telefonoss found with findByNombre('redmi'):");
			System.out.println("--------------------------------------------");
			telefonoRepository.findByName("redmi").forEach(phone -> {
				System.out.println(phone.toString());
			});
			System.out.println("");

		};
	}

}
