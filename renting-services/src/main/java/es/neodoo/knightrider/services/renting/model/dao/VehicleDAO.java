package es.neodoo.knightrider.services.renting.model.dao;

import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;

public interface VehicleDAO {

	List<Vehicle> getVehiclesUnblocked() throws DAOException;

	Vehicle getVehicleWithStatusBlocked(String username) throws DAOException;

	Vehicle getVehicleWithStatusTraveling(String username) throws DAOException;

	Vehicle getVehicle(int vehicleId) throws DAOException;

	Vehicle updateVehicleToUnblocked(int vehicleId) throws DAOException;

	Vehicle updateVehicleToBlocked(int vehicleId) throws DAOException;

	Vehicle updateVehicleToTraveling(int vehicleId) throws DAOException;

}