package es.alex.learning.repos.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.alex.learning.classes.h2.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {

	  List<Dog> findByName(String name);

	  Dog findById(long id);
	}