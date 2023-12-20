package es.alex.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

@RestController
public class CommandController {
	
	@Autowired
	@Qualifier("hazelcastInstance")
	HazelcastInstance hazelcastInstance;
	

	@PostMapping("/put")
	public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
		hazelcastInstance.getMap("my-distributed-map").put(key, value);
		return new String(value);
	}

	@GetMapping("/get")
	public String get(@RequestParam(value = "key") String key) {
		String value = (String)hazelcastInstance.getMap("my-distributed-map").get(key);
		return new String(value);
	}
}