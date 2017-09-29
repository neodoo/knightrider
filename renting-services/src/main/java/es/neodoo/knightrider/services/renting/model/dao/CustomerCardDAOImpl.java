package es.neodoo.knightrider.services.renting.model.dao;

import java.util.Date;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Customer;
import es.neodoo.knightrider.services.renting.model.vo.CustomerCard;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class CustomerCardDAOImpl implements CustomerCardDAO {

	private static final Log log = LogFactory.getLog(CustomerCardDAOImpl.class);

	private EntityManager em;

	public CustomerCardDAOImpl() {
		super();
	}	

	@Override
	public void createCreditCard(String email, String creditCardNumber, String creditCardName, int creditCardCVS, Date creditCardDate) throws DAOException {

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();
			
			Customer customer = em.getReference(Customer.class, email);
			customer.setEmail(email);

			CustomerCard customerCard = new CustomerCard();	
			customerCard.setNumber(creditCardNumber);
			customerCard.setName(creditCardName);
			customerCard.setCvs(creditCardCVS);
			customerCard.setDateExpired(creditCardDate);
			
			em.persist(customerCard);
			
			log.debug("insert Credit card into BD: " + email + creditCardNumber);

		} catch(TransactionRequiredException  | IllegalArgumentException | EntityExistsException  e) {
			log.error("Error inserting creditCard into BD " + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

	}

}
