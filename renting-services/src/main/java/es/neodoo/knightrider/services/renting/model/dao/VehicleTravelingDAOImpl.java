package es.neodoo.knightrider.services.renting.model.dao;

import java.sql.Timestamp;

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
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class VehicleTravelingDAOImpl implements VehicleTravelingDAO {
	
	private static final Log log = LogFactory.getLog(VehicleTravelingDAOImpl.class);
	
	private EntityManager em;
	
	@Override
	public VehicleTraveling getVehicleTraveling(String username) throws DAOException {

		VehicleTraveling vehicleTraveling = null;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT v FROM VehicleTraveling v WHERE v.customer.email = :user";

			Query query = em.createQuery(jpql);
			query.setParameter("user", username);

			vehicleTraveling = (VehicleTraveling) query.getSingleResult();

			log.debug("User (" + username + ") are traveling with vehicle: " + vehicleTraveling.toString());

		} catch(NoResultException e){
			log.debug("Vehicle traveling not found for user " + username);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error selecting vehicleTraveling: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicleTraveling;

	}

	@Override
	public void createVehicleTraveling(String username, int vehicleId,Timestamp actualTime, Vehicle vehicle) throws DAOException {

		try {
			
			em = PersistenceListener.createEntityManager();

			VehicleTraveling vehicleTraveling = new VehicleTraveling();
			Customer customer = new Customer();
			customer.setEmail(username);
			vehicleTraveling.setCustomer(customer);
			vehicleTraveling.setVehicle(vehicle);
			vehicleTraveling.setDateStart(actualTime);
			vehicleTraveling.setLatitudeStart(vehicle.getLatitude());
			vehicleTraveling.setLongitudeStart(vehicle.getLongitude());
			vehicleTraveling.setBatteryStart(vehicle.getBatteryLevel());
			em.persist(vehicleTraveling);


			log.debug("Insert into vehicleTraveling user: " + username + "vehicleId: " + vehicleId + "time: " + actualTime + " vehicle: " + vehicle.toString());

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting vehicle traveling " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

	}
	
	@Override
	public void deleteVehicleTraveling(int vehicleId) throws DAOException {

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "DELETE FROM VehicleTraveling v WHERE v.vehicle.id = :id";
			Query query = em.createQuery(jpql);
			query.setParameter("id", vehicleId);
			query.executeUpdate();

			log.debug("Delete from Vehicletraveling vehicleId: " + vehicleId);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e){
			log.error("Error deleting vehicle traveling " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

	}

}
