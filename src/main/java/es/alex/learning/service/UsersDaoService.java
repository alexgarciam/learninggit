package es.alex.learning.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import es.alex.learning.classes.Usuarios;

@Component
public class UsersDaoService {

	private static List<Usuarios> users = new ArrayList<>();

	private static Long usersCount = new Long(3);

	static {
		users.add(new Usuarios("alex", "Bauer", "alex", "sa"));
		users.add(new Usuarios("pepe", "O'Brian", "pepe", "sa"));
		users.add(new Usuarios("paco", "O'Brian", "paco", "sa"));
	}

	public List<Usuarios> findAll() {
		return users;
	}

	public Usuarios save(Usuarios user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public Usuarios findOne(int id) {
		for (Usuarios user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public Usuarios deleteById(int id) {

		Iterator<Usuarios> iterator = users.iterator();
		while (iterator.hasNext()) {
			Usuarios user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
