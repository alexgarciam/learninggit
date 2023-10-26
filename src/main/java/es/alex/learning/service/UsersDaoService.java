package es.alex.learning.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class UsersDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;

	static {
		users.add(new User(1, "Rafa", new Date()));
		users.add(new User(2, "Roger", new Date()));
		users.add(new User(3, "Novack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}
}
