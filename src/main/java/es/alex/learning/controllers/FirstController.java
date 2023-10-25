package es.alex.learning.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> modify-first-controller
import org.springframework.web.bind.annotation.ResponseBody;
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
	
<<<<<<< HEAD
	@GetMapping("/hello3")
	@ResponseBody
	public String helloWorld3(@RequestParam() String name) {
		return "hello "+name;
	}
	
	@GetMapping("/hello4/{name}")
	@ResponseBody
	public String helloWorld4(@PathVariable String name) {
		return "hello "+name;
	}

=======
	
	@GetMapping("/usuarios")
	public List<String> getUsuarios() {
		return Arrays.asList("Alex","Manuel","Benzema","paco");
	}
	
	@GetMapping("/hello1")
	@ResponseBody
	public String helloWorld1() {
		return "hello Alex";
	}
>>>>>>> modify-first-controller
	
}
