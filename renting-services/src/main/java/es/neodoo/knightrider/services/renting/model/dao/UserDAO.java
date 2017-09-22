package es.neodoo.knightrider.services.renting.model.dao;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;

public interface UserDAO {

	void createUser(String email, String pass) throws DAOException;

}