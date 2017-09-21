package es.neodoo.knightrider.services.renting.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.*;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;

public class CustomerDAOImpl implements Serializable, CustomerDAO {

	private static final long serialVersionUID = 1L;

	static private final Log log = LogFactory.getLog(VehicleDAOImpl.class);

	private EntityManager em;

	public CustomerDAOImpl() {
		super();
	}

	public void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber, Date driveDate) throws DAOException {

		try {

			String jpql = "INSERT INTO customer (email, name, surname, birthdate, mobile, driving_license_id, driving_license_date) VALUES(?,?,?,?,?,?,?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, email);
			query.setParameter(3, name);
			query.setParameter(4, surname);
			query.setParameter(5, birthDate);
			query.setParameter(6, phone);
			query.setParameter(7, driveNumber);
			query.setParameter(8, driveDate);
			query.executeUpdate();

			log.debug("insert new customer into BD: " + email + name);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e){
			log.error("Error inserting customer into BD " + e);
			throw new DAOException(e);
		}

	}

	public void createUser(String email, String pass) throws DAOException {

		try {

			String jpql = "INSERT INTO user (email, password ) VALUES(?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, email);
			query.setParameter(2, pass);
			query.executeUpdate();

			log.debug("insert new customer into BD: " + email + pass);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting user into BD " + e);
			throw new DAOException(e);
		}

	}

	public void createCreditCard(String username, String creditCardNumber, String creditCardName, int creditCardCVS, Date creditCardDate) throws DAOException {

		try {

			String jpql = "INSERT INTO customer_card (id, email, number, name, cvs, date_expired, active) VALUES(?,?,?,?,?,?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, null);
			query.setParameter(2, username);
			query.setParameter(3, creditCardNumber);
			query.setParameter(4, creditCardName);
			query.setParameter(5, creditCardCVS);
			query.setParameter(6, creditCardDate);
			query.setParameter(7, true);
			query.executeUpdate();

			log.debug("insert Credit card into BD: " + username + creditCardNumber);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting creditCard into BD " + e);
			throw new DAOException(e);
		}

	}

	public long getNumTravels(String username) throws DAOException {

		long num_travels = 0;

		try {

			String jpql = "SELECT count(*) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);		

			num_travels =  (Long) query.getSingleResult();

			log.debug("geting numTravels of user: "+ username +" = " + num_travels);

		} catch (NoResultException e) {
			log.debug("No travels for user:" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting  num travels: " + e);
			throw new DAOException(e);
		}

		return num_travels;

	}

	public double getCost(String username) throws DAOException {

		double cost = 0;

		try {

			String jpql = "SELECT SUM(v.cost) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);

			cost =  (Double) query.getSingleResult();

			log.debug("geting cost of user: "+ username + " = " + cost);

		} catch (NoResultException e) {
			log.debug("No travels for user" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting cost: " + e);
			throw new DAOException(e);
		}

		return cost;

	}

	public double getTime(String username) throws DAOException {

		double time =0;

		try {

			String jpql = "SELECT SUM(v.time) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);		

			time =  (Double) query.getSingleResult();

		} catch (NoResultException e) {
			log.debug("No travels for user" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting time: " + e);
			throw new DAOException(e);
		}

		return time;

	}

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

	@SuppressWarnings("unchecked")
	public List<VehicleTravel> getTravels(String username) throws DAOException {

		List<VehicleTravel> travels = null;

		try{

			String jpql = "SELECT v FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);	

			travels = query.getResultList();

			log.debug("geting Travels of user: " + " = " + travels.toString());

		} catch (NoResultException e) {
			log.debug("user dont has tarvels:" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting time: " + e);
			throw new DAOException(e);
		}

		return travels;

	}

}