package es.neodoo.knightrider.services.renting.model.dao;

import java.util.Date;
import java.util.List;
import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;

public interface CustomerDAO {

	/**
	 * 
	 * @param email
	 * @param name
	 * @param surname
	 * @param birthDate
	 * @param phone
	 * @param driveNumber
	 * @param driveDate
	 * @throws DAOException
	 * Insert a new customer into BD 
	 */
	void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber,
			Date driveDate) throws DAOException;

	/**
	 * 
	 * @param email
	 * @param pass
	 * @throws DAOException
	 * insert new user into BD
	 */
	void createUser(String email, String pass) throws DAOException;

	/**
	 * 
	 * @param username
	 * @param creditCardNumber
	 * @param creditCardName
	 * @param creditCardCVS
	 * @param creditCardDate
	 * @throws DAOException
	 * Insert a new credit card into BD
	 */
	void createCreditCard(String username, String creditCardNumber, String creditCardName, int creditCardCVS,
			Date creditCardDate) throws DAOException;

	/**
	 * 
	 * @param username
	 * @return the number of travels of username
	 * @throws DAOException 
	 */
	long getNumTravels(String username) throws DAOException;

	/**
	 * 
	 * @param username
	 * @return the sum of the cost of all travels of username
	 * @throws DAOException 
	 */
	double getCost(String username) throws DAOException;

	/**
	 * 
	 * @param username
	 * @return the sum of the time of all travels of username
	 * @throws DAOException 
	 */
	double getTime(String username) throws DAOException;

	/**
	 * 
	 * @param username
	 * @return the name of the customer username
	 * @throws DAOException  
	 */
	String getName(String username) throws DAOException;

	/**
	 * 
	 * @param username
	 * @return the list of all travels of username
	 * @throws DAOException  
	 */
	List<VehicleTravel> getTravels(String username) throws DAOException;

}