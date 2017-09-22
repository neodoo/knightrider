package es.neodoo.knightrider.services.renting.model.dao;

import java.sql.Timestamp;
import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public interface VehicleTravelDAO {

	void createTravel(VehicleTraveling vehicleTraveling, Vehicle vehicle, Timestamp dateEnd, Double cost, double time)
			throws DAOException;

	long countTravels(String username) throws DAOException;

	double getCost(String username) throws DAOException;
	
	double getTime(String username) throws DAOException;

	List<VehicleTravel> getTravels(String username) throws DAOException;

}