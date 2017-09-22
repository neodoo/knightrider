package es.neodoo.knightrider.services.renting.model.dao;

import java.sql.Timestamp;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public interface VehicleTravelingDAO {

	VehicleTraveling getVehicleTraveling(String username) throws DAOException;

	void createVehicleTraveling(String username, int vehicleId, Timestamp actualTime, Vehicle vehicle)
			throws DAOException;

	void deleteVehicleTraveling(int vehicleId) throws DAOException;

}