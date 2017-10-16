package es.neodoo.knightrider.services.renting.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Rol;
import es.neodoo.knightrider.services.renting.model.vo.User;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class UserDAOImpl implements UserDAO {

	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	private EntityManager em;
	
	public UserDAOImpl() {
		super();
	}

	@Override
	public void createUser(String email, String pass) throws DAOException {

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();
			
			User user = new User();			
			List<Rol> rol = em.createNamedQuery("Rol.findAll", Rol.class).getResultList();
			user.setRols(rol);
			user.setEmail(email);
			user.setPassword(pass);
			
			em.persist(user);
			log.debug("insert new user into BD: " + email + pass);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting user into BD " + e);
			throw new DAOException(e);
		} catch(NullPointerException e1){
			log.error("Error inserting user into BD because null pointer " + e1);
			throw new DAOException(e1);
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}

	}

}
