package es.neodoo.knightrider.services.renting.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Customer;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleBlocked;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class VehicleBlockedDAOImpl implements VehicleBlockedDAO {

	private static final Log log = LogFactory.getLog(VehicleBlockedDAOImpl.class);

	private EntityManager em;
	
	public VehicleBlockedDAOImpl() {
		super();
	}

	@Override
	public VehicleBlocked getVehicleBlocked(String username) throws DAOException {

		VehicleBlocked vehicleBlocked = null;

		try {

			em = PersistenceListener.createEntityManager();
			
			String jpql = "SELECT v FROM VehicleBlocked v WHERE v.customer.email = :email";

			Query query = em.createQuery(jpql);
			query.setParameter("email", username);


			vehicleBlocked = (VehicleBlocked) query.getSingleResult();

			log.debug("user: " + username + "blocked vehicle: " + vehicleBlocked.toString());

		} catch(NoResultException e){
			log.debug("Vehicle blocked not found for user " + username);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error selecting vehicleBlocked : " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicleBlocked;

	}

	@Override
	public void createVehicleBlocked(String username, int vehicleId) throws DAOException {

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();

			Customer customer = em.getReference(Customer.class, username);
			Vehicle vehicle = em.getReference(Vehicle.class, vehicleId); 
			
			VehicleBlocked vehicleBlocked = new VehicleBlocked();
			vehicleBlocked.setCustomer(customer);
			vehicleBlocked.setVehicle(vehicle);
			
			em.persist(vehicleBlocked);

			log.debug("Insert into VehicleBlocked user: " + username + "vehicleId: " + vehicleId);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) { 
			log.error("Error inserting vehicle blocked" + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

	}

	@Override
	public void deleteVehicleBlocked(int vehicleId) throws DAOException {

		try {		

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();

			String jpql = "DELETE FROM VehicleBlocked v WHERE v.vehicle.id = :id";
			Query query = em.createQuery(jpql);
			query.setParameter("id", vehicleId);
			query.executeUpdate();

			log.debug("Delete from VehicleBlocked vehicleId: " + vehicleId);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error deleting vehicle blocked " + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

	}

}
