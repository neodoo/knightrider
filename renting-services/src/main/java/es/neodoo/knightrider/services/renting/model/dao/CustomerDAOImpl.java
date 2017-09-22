package es.neodoo.knightrider.services.renting.model.dao;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.*;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	static private final Log log = LogFactory.getLog(CustomerDAOImpl.class);

	private EntityManager em;

	public CustomerDAOImpl() {
		super();
	}

	@Override
	public void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber, Date driveDate) throws DAOException {

		try {

			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setName(name);
			customer.setSurname(surname);
			customer.setBirthdate(birthDate);
			customer.setMobile(phone);
			customer.setDrivingLicenseId(driveNumber);
			customer.setDrivingLicenseDate(driveDate);
			em.persist(customer);

			log.debug("insert new customer into BD: " + email + name);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e){
			log.error("Error inserting customer into BD " + e);
			throw new DAOException(e);
		}

	}

	@Override
	public String getName(String username) throws DAOException {

		String name = "";

		try{

			String jpql = "SELECT c.name FROM Customer c WHERE c.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);		

			name =  (String) query.getSingleResult();

			log.debug("geting name of user: " + username + " = " +  name);

		} catch (NoResultException e) {
			log.debug("Dont exist user :" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting time: " + e);
			throw new DAOException(e);
		}

		return name;

	}

}