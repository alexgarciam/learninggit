package es.alex.learning.repos.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.alex.learning.classes.mysql.Telefonos;

public interface TelefonoRepository extends JpaRepository<Telefonos, Long> {

	  List<Telefonos> findByName(String nombre);
	  
	  List<Telefonos> findByMarca(String marca);

	  Telefonos findById(long id);
	}