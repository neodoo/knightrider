package es.neodoo.knightrider.services.renting.model.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.logging.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;
import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleBlocked;


public class VehicleDAOImpl implements Serializable, VehicleDAO {

	private static final long serialVersionUID = 1L;

	private final String VEHICLE_STATE_ONLINE = "online";

	private final String VEHICLE_RENT_UNBLOCK = "unblocked";

	private final String VEHICLE_RENT_BLOCK ="blocked";

	private final String VEHICLE_RENT_TRAVELING ="traveling";

	private static final Log log = LogFactory.getLog(VehicleDAOImpl.class);

	private EntityManager em;

	public VehicleDAOImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehiclesUnblocked() throws DAOException {

		List<Vehicle> vehicles = null;

		try {

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
		}

		return vehicles;

	}

	public Vehicle getVehicleBlocked(String username) throws DAOException {

		Vehicle vehicle = null;

		try {

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
		}

		return vehicle;

	}

	public Vehicle getVehicleTraveling(String username) throws DAOException {

		Vehicle vehicle = null;

		try {

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
		}

		return vehicle;

	}

	public Vehicle selectVehicle(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try {

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
		} 

		return vehicle;

	}

	public VehicleBlocked selectVehicleBlocked(String username) throws DAOException {

		VehicleBlocked vehicleBlocked = null;

		try{

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
		}

		return vehicleBlocked;

	}

	public VehicleTraveling selectVehicleTraveling(String username) throws DAOException {

		VehicleTraveling vehicleTraveling = null;

		try {

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
		}

		return vehicleTraveling;

	}

	public Vehicle updateVehicleToUnblocked(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try {

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_UNBLOCK);
			em.persist(vehicle);

			log.debug("update rentState to: " + VEHICLE_RENT_UNBLOCK+ "of the vehicle with id: " + vehicleId);


		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e) {
			log.error("Error updating BD" + e);
			throw new DAOException(e);
		}

		return vehicle;

	}

	public Vehicle updateVehicleToBlocked(int vehicleId) throws DAOException{

		Vehicle vehicle = null;

		try {

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_BLOCK);
			em.persist(vehicle); 

			log.debug("update rentState to: " + VEHICLE_RENT_BLOCK+ "of the vehicle with id: " + vehicleId);

		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e) {
			log.error("Error updating vehicle to blocked" + e);
			throw new DAOException(e);
		}

		return vehicle;

	}

	public Vehicle updateVehicleToTraveling(int vehicleId) throws DAOException {

		Vehicle vehicle = null;

		try{

			vehicle = em.find(Vehicle.class, vehicleId);
			vehicle.setRentState(VEHICLE_RENT_TRAVELING);
			em.persist(vehicle); 

		} catch(IllegalStateException | EntityExistsException | TransactionRequiredException e){
			log.error("Error updating vehicle to traveling" + e);
			throw new DAOException(e);
		}

		return vehicle;

	}

	public void insertVehicleBlocked(String username, int vehicleId) throws DAOException {

		try {

			String jpql = "INSERT INTO vehicle_blocked(id, email, id_vehicle) VALUES(?,?,?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, null);
			query.setParameter(2, username);
			query.setParameter(3, vehicleId);
			query.executeUpdate();

			log.debug("Insert into VehicleBlocked user: " + username + "vehicleId: " + vehicleId);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) { 
			log.error("Error inserting vehicle blocked" + e);
			throw new DAOException(e);
		}

	}

	public void insertVehicleTraveling(String username, int vehicleId,Timestamp actualTime, Vehicle vehicle) throws DAOException {

		try {

			String jpql = "INSERT INTO vehicle_rented (id, email, id_vehicle, date_start, battery_start, latitude_start, longitude_start) VALUES(?,?,?,?,?,?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, null);
			query.setParameter(2, username);
			query.setParameter(3, vehicleId);
			query.setParameter(4, actualTime);
			query.setParameter(5, vehicle.getBatteryLevel());
			query.setParameter(6, vehicle.getLatitude());
			query.setParameter(7, vehicle.getLongitude());
			query.executeUpdate();

			log.debug("Insert into vehicleTraveling user: " + username + "vehicleId: " + vehicleId + "time: " + actualTime + " vehicle: " + vehicle.toString());

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting vehicle traveling " + e);
			throw new DAOException(e);
		}

	}

	public void insertTravel(VehicleTraveling vehicleTraveling, Vehicle vehicle, Timestamp dateEnd, Double cost, double time) throws DAOException {

		try {

			String jpql = "INSERT INTO vehicle_travel (id, id_vehicle, email, date_start, latitude_start, "
					+ "longitude_start, date_end, latitude_end, longitude_end, cost, time) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			Query query = em.createNativeQuery(jpql);
			query.setParameter(1, null);
			query.setParameter(2, vehicleTraveling.getVehicle().getId());
			query.setParameter(3, vehicleTraveling.getCustomer().getEmail());
			query.setParameter(4, vehicleTraveling.getDateStart());
			query.setParameter(5, vehicleTraveling.getLatitudeStart());
			query.setParameter(6, vehicleTraveling.getLongitudeStart());
			query.setParameter(7, dateEnd);
			query.setParameter(8, vehicle.getLatitude());
			query.setParameter(9, vehicle.getLongitude());
			query.setParameter(10, cost);
			query.setParameter(11, time);
			query.executeUpdate();

			log.debug("insert into vehicle travel: " + vehicleTraveling.toString() + dateEnd + vehicle.toString() + cost + time);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting vehicleTraveling " + e);
			throw new DAOException(e);
		}

	}

	public void deleteVehicleBlocked(int vehicleId) throws DAOException {

		try {		

			String jpql = "DELETE FROM VehicleBlocked v WHERE v.vehicle.id = :id";
			Query query = em.createQuery(jpql);
			query.setParameter("id", vehicleId);
			query.executeUpdate();

			log.debug("Delete from VehicleBlocked vehicleId: " + vehicleId);


		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error deleting vehicle blocked " + e);
			throw new DAOException(e);
		}

	}

	public void deleteVehicleTraveling(int vehicleId) throws DAOException {

		try {

			String jpql = "DELETE FROM VehicleTraveling v WHERE v.vehicle.id = :id";
			Query query = em.createQuery(jpql);
			query.setParameter("id", vehicleId);
			query.executeUpdate();

			log.debug("Delete from Vehicletraveling vehicleId: " + vehicleId);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e){
			log.error("Error deleting vehicle traveling " + e);
			throw new DAOException(e);
		}

	}

}
