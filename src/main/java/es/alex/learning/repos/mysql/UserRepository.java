package es.alex.learning.repos.mysql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.alex.learning.classes.mysql.Usuarios;

public interface UserRepository extends CrudRepository<Usuarios, Long> {

	  List<Usuarios> findByLogin(String login);

	  Usuarios findById(long id);
	}