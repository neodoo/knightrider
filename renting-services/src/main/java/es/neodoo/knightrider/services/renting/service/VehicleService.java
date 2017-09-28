package es.neodoo.knightrider.services.renting.service;

import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.exceptions.UserHasAnotherVehicleBlocked;
import es.neodoo.knightrider.services.renting.exceptions.VehicleIsNotAvailable;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;

public interface VehicleService {

	List<Vehicle> getVehiclesWithStatusUnblocked() throws ServiceException;

	Vehicle getVehicleWithStatusBlocked(String username) throws ServiceException;

	Vehicle getVehicleWithStatusTraveling(String username) throws ServiceException;

	boolean block(String username, int vehicleId)
			throws ServiceException, UserHasAnotherVehicleBlocked, VehicleIsNotAvailable;

	boolean unblock(String username, int vehicleId) throws ServiceException;

	boolean travelStart(String username, int vehicleId)
			throws VehicleIsNotAvailable, UserHasAnotherVehicleBlocked, ServiceException;

	double travelFinish(String username, int vehicleId) throws ServiceException;

}