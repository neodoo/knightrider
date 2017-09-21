package es.neodoo.knightrider.services.renting.model.dao;

import java.sql.Timestamp;
import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleBlocked;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public interface VehicleDAO {

	/**
	 * 
	 * @param em
	 * @return a list of the unblocked vehicles
	 */
	List<Vehicle> getVehiclesUnblocked() throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @return if exist the vehicle blocked by username
	 * @throws DAOException 
	 */
	Vehicle getVehicleBlocked(String username) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @return if exist the vehicle traveling by username
	 * @throws DAOException 
	 */
	Vehicle getVehicleTraveling(String username) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @return the vehicle with vehicleId
	 * @throws DAOException 
	 */
	Vehicle selectVehicle(int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @return the vehicleBlocked by the username if exist
	 * @throws DAOException 
	 */
	VehicleBlocked selectVehicleBlocked(String username) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @return the VehicleTraveling by username
	 * @throws DAOException 
	 */
	VehicleTraveling selectVehicleTraveling(String username) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @retun the vehicle with vehicleId and update its rent_state to "unblock"
	 * @throws DAOException 
	 */
	Vehicle updateVehicleToUnblocked(int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @return the vehicle with vehicleId and update its rent_state to "blocked"
	 * @throws DAOException 
	 */
	Vehicle updateVehicleToBlocked(int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @return the vehicle with vehicleId and update its rent_state to "traveling"
	 * @throws DAOException 
	 */
	Vehicle updateVehicleToTraveling(int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @param vehicleId
	 * @throws DAOException 
	 * Insert into vehicle_reserved a new vehicle blocked.
	 */
	void insertVehicleBlocked(String username, int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param username
	 * @param vehicleId
	 * @param actualTime
	 * @param vehicle
	 * @throws DAOException
	 * Insert into vehicle_rented a new travel
	 */
	void insertVehicleTraveling(String username, int vehicleId, Timestamp actualTime, Vehicle vehicle)
			throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleRented
	 * @param vehicle
	 * @param timestamp
	 * @param cost
	 * @param time
	 * @throws DAOException 
	 * Insert into Vehicle_travel a new travel
	 */
	void insertTravel(VehicleTraveling vehicleTraveling, Vehicle vehicle, Timestamp dateEnd, Double cost, double time)
			throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @param username
	 * @throws DAOException 
	 * Delete the VehicleBlocked with vehicleId and username
	 */
	void deleteVehicleBlocked(int vehicleId) throws DAOException;

	/**
	 * 
	 * @param em
	 * @param vehicleId
	 * @throws DAOException 
	 * Delete the VehicleTraveling with vehicleId.
	 */
	void deleteVehicleTraveling(int vehicleId) throws DAOException;

}