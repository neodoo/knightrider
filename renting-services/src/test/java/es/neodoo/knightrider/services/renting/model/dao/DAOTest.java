package es.neodoo.knightrider.services.renting.model.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class DAOTest {

	private static final String PERSISTENCE_UNIT = "KnightriderPU";
				
	//@Test
	public void connectToDatabase() {
		
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			EntityManager em = emf.createEntityManager();
			
		} catch (Exception e) {
			fail("can not connect to database: " + e);
		}
				
	}

}
