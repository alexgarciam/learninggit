package es.alex.learning.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.alex.learning.classes.Usuarios;

public interface UserRepository extends CrudRepository<Usuarios, Long> {

	  List<Usuarios> findByLogin(String login);

	  Usuarios findById(long id);
	}