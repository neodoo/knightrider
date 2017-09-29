package es.neodoo.knightrider.services.renting.model.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class VehicleDAOImpl implements VehicleDAO {

	private final String VEHICLE_STATE_ONLINE = "online";

	private final String VEHICLE_RENT_UNBLOCK = "unblocked";

	private final String VEHICLE_RENT_BLOCK ="blocked";

	private final String VEHICLE_RENT_TRAVELING ="traveling";

	private static final Log log = LogFactory.getLog(VehicleDAOImpl.class);
	
	private EntityManager em;

	public VehicleDAOImpl() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehiclesUnblocked() throws DAOException {
		
		List<Vehicle> vehicles = null;

		try {
			
			em = PersistenceListener.createEntityManager();
			
			String jpql = "SELECT v FROM Vehicle v WHERE v.state = :state AND v.rentState = :rentState";

			Query query = em.createQuery(jpql);
			query.setParameter("state", VEHICLE_STATE_ONLINE);		
			query.setParameter("rentState", VEHICLE_RENT_UNBLOCK);

			vehicles = (List<Vehicle>) query.getResultList();

			log.debug("Selected all unblocked vehicles"+ vehicles.toString());

		} catch (NoResultException e) {
			log.debug("No vehicles unblocked", e);
			
		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting vehicles unblocked: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicles;

	}

	@Override
	public Vehicle getVehicleWithStatusBlocked(String username) throws DAOException {

		Vehicle vehicle = null;

		try {
			
			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT v FROM Vehicle v " + 
					" WHERE v.id = ( SELECT vr.vehicle FROM VehicleBlocked  vr WHERE vr.customer.email = :user) ";

			Query query = em.createQuery(jpql);
			query.setParameter("user", username);			

			vehicle = (Vehicle) query.getSingleResult();

			log.debug("Vehicle blocked from username " + username + ": " + vehicle.toString());

			
			
		} catch (NoResultException e) {
			log.debug("Vehicle blocked not found for username " + username);
		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting the vehicleBlocked:" + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicle;

	}

	@Override
	public Vehicle getVehicleWithStatusTraveling(String username) throws DAOException {

		Vehicle vehicle = null;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT v FROM Vehicle v WHERE v.id = "
					+ "( SELECT vr.vehicle FROM VehicleTraveling  vr WHERE vr.customer.email = :user) ";

			Query query = em.createQuery(jpql);
			query.setParameter("user", username);

			vehicle = (Vehicle) query.getSingleResult();

			log.debug("User: "+ username + "are traveling with vehicle: " + vehicle.toString());

		} catch (NoResultException e) {
			log.debug("Vehicle traveling not found for username " + username);
		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("error getting the vehicleTraveling: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicle;

	}

	@Override
	public Vehicle getVehicle(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT v FROM Vehicle v WHERE v.id= :vehicle ";

			Query query = em.createQuery(jpql);
			query.setParameter("vehicle", vehicleId);

			vehicle = (Vehicle) query.getSingleResult();

			log.debug("Vehicle with id ( " + vehicleId + ") = " + vehicle.toString());

		} catch(NoResultException e){
			log.debug("Vehicle not found with id " + vehicleId);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error selecting vehicle (" + vehicleId + "): " +  e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return vehicle;

	}

	@Override
	public Vehicle updateVehicleToUnblocked(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_UNBLOCK);
			
			em.persist(vehicle);

			log.debug("update rentState to: " + VEHICLE_RENT_UNBLOCK+ "of the vehicle with id: " + vehicleId);


		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e) {
			log.error("Error updating BD" + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

		return vehicle;

	}

	@Override
	public Vehicle updateVehicleToBlocked(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_BLOCK);
			
			em.persist(vehicle); 
			log.debug("update rentState to: " + VEHICLE_RENT_BLOCK+ "of the vehicle with id: " + vehicleId);

		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e) {
			log.error("Error updating vehicle to blocked" + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}


		return vehicle;

	}

	@Override
	public Vehicle updateVehicleToTraveling(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try{

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_TRAVELING);
			
			em.persist(vehicle); 

		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e) {
			log.error("Error updating vehicle to traveling" + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

		return vehicle;

	}

}
