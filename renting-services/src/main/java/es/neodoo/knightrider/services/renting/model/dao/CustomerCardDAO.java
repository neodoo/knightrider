package es.neodoo.knightrider.services.renting.model.dao;

import java.util.Date;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;

public interface CustomerCardDAO {

	void createCreditCard(String username, String creditCardNumber, String creditCardName, int creditCardCVS,
			Date creditCardDate) throws DAOException;

}