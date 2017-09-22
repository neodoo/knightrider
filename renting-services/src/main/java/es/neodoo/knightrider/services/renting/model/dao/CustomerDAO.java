package es.neodoo.knightrider.services.renting.model.dao;

import java.util.Date;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;

public interface CustomerDAO {

	void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber,
			Date driveDate) throws DAOException;

	String getName(String username) throws DAOException;

}