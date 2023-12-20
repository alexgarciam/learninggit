package es.alex.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class DataController {

	@Autowired
	es.alex.learning.service.UsersDaoService UsersDaoService;
	
	@GetMapping("/getUsersFromDatabase")
	public String getUsersFromDatabase() {
	    Gson gson = new Gson();
		return gson.toJson(UsersDaoService.findAll());
	}
}
