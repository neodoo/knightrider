package es.neodoo.knightrider.services.renting.model.dao;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.VehicleBlocked;

public interface VehicleBlockedDAO {

	VehicleBlocked selectVehicleBlocked(String username) throws DAOException;

	void createVehicleBlocked(String username, int vehicleId) throws DAOException;

	void deleteVehicleBlocked(int vehicleId) throws DAOException;

}