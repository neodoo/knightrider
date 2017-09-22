package es.neodoo.knightrider.services.renting.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.User;

public class UserDAOImpl implements UserDAO {

	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	private EntityManager em;

	@Override
	public void createUser(String email, String pass) throws DAOException {

		try {

			User user = new User();
			user.setEmail(email);
			user.setPassword(pass);
			em.persist(user);

			log.debug("insert new user into BD: " + email + pass);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting user into BD " + e);
			throw new DAOException(e);
		}

	}

}
