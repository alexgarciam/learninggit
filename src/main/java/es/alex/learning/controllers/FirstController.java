package es.alex.learning.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	
	@GetMapping("/helloworld")
	public String helloWorldBean() {
		return "hola Mundo!";
	}
	
	@GetMapping("/users")
	public List<String> getUsers() {
		return Arrays.asList("Alex","Manuel","Benzema","paco");
	}
}
