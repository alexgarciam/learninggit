package es.alex.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	
	@GetMapping("/helloworld")
	public String helloWorldBean() {
		return "hola Mundo!";
	}
}
