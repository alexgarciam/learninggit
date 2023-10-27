package es.alex.learning.jpa;


import org.springframework.stereotype.Repository;

import es.alex.learning.classes.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class PersonJPARepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public Country findById(int id) {
		return entityManager.find(Country.class, id);
	}
	
	public Country insert (Country country) {
		entityManager.persist(country);
		return country;
	}
	
}
