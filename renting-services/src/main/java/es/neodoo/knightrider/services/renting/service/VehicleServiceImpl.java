package es.neodoo.knightrider.services.renting.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.exceptions.UserHasAnotherVehicleBlocked;
import es.neodoo.knightrider.services.renting.exceptions.VehicleIsNotAvailable;
import es.neodoo.knightrider.services.renting.model.dao.VehicleBlockedDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleBlockedDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.VehicleDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelingDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelingDAOImpl;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleBlocked;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public class VehicleServiceImpl implements VehicleService {

	VehicleDAO vehicleDAO = new VehicleDAOImpl();;

	VehicleBlockedDAO vehicleBlockedDAO = null;

	VehicleTravelingDAO vehicleTravelingDAO = null;

	VehicleTravelDAO vehicleTravelDAO = null;

	private final String VEHICLE_RENT_UNBLOCK = "unblocked";

	private final int NUMBER_OF_DECIMALS = 2;

	private final double PRICE_PER_MINUT = 0.21;

	private final int CONVERT_MS_TO_SECONDS = 60000;

	private static final Log log = LogFactory.getLog(VehicleServiceImpl.class);

	public VehicleServiceImpl(){
		
		super();
		vehicleDAO = new VehicleDAOImpl();;
		vehicleBlockedDAO = new VehicleBlockedDAOImpl();
		vehicleTravelingDAO = new VehicleTravelingDAOImpl();
		vehicleTravelDAO = new VehicleTravelDAOImpl();

	}

	@Override
	public List<Vehicle> getVehiclesWithStatusUnblocked() throws ServiceException {

		List<Vehicle> vehicles = null;

		try {

			vehicles = vehicleDAO.getVehiclesUnblocked();
			log.info("vehicles unblocked:" + vehicles.toString());

		} catch(DAOException e){
			throw new ServiceException("Can not get vehicles unblocked: " + e);
		}

		return vehicles;

	}

	@Override
	public Vehicle getVehicleWithStatusBlocked(String username) throws ServiceException {

		Vehicle vehicle = null;

		try {

			log.info("DENTRO DE LOS SERVICES");
			vehicle = vehicleDAO.getVehicleWithStatusBlocked(username);
			log.info("vehicle blocked by "+ username +" : " + vehicle.toString());

		} catch(DAOException e){
			throw new ServiceException("Can not get vehicle blocked: " + e);
		}

		return vehicle;

	}

	@Override
	public Vehicle getVehicleWithStatusTraveling(String username) throws ServiceException {

		Vehicle vehicle = null;

		try {

			vehicle = vehicleDAO.getVehicleWithStatusTraveling(username);
			log.info("vehicle traveling by "+ username +" : " + vehicle.toString());

		} catch(DAOException e){
			throw new ServiceException("Can not get vehicle Traveling: " + e);
		}

		return vehicle;

	}

	@Override
	public boolean block(String username, int vehicleId) throws ServiceException, UserHasAnotherVehicleBlocked, VehicleIsNotAvailable {

		boolean blocked = false;	
		VehicleBlocked vehicleBlocked = null;
		Vehicle vehicle = null;

		try {

			vehicleBlocked = vehicleBlockedDAO.getVehicleBlocked(username);

			if (vehicleBlocked != null){

				log.debug("the user has another vehicle blocked");
				throw new UserHasAnotherVehicleBlocked("User has another vehicle Blocked");

			} else {

				vehicle = vehicleDAO.getVehicle(vehicleId);

				if(vehicle.getRentState().equals(VEHICLE_RENT_UNBLOCK)){

					vehicleDAO.updateVehicleToBlocked(vehicleId);
					vehicleBlockedDAO.createVehicleBlocked(username, vehicleId);
					blocked = true;	// Ok, vehicle is blocked for this user
					log.debug("User " + username + " block the vehicle " + vehicleId + "correctly");

				} else {

					log.debug("this vehicle is not available because another user blocked it.");
					throw new VehicleIsNotAvailable("Vehicle : " + vehicleId + "is already blocked");
				}

			}

		} catch(DAOException e) {
			throw new ServiceException("Can not block this vehicle: "+ vehicleId  + " : " + e);
		}

		return blocked;

	}

	@Override
	public boolean unblock(String username, int vehicleId) throws ServiceException {

		boolean unblocked = false;	
		VehicleBlocked vehicleBlocked = null;

		try {

			vehicleBlocked = vehicleBlockedDAO.getVehicleBlocked(username);

			if (vehicleBlocked != null){

				vehicleDAO.updateVehicleToUnblocked(vehicleId);
				vehicleBlockedDAO.deleteVehicleBlocked(vehicleId);
				unblocked = true;
				log.debug("User " + username + "unblock vehicle " + vehicleId + "correctly");

			} else { 

				unblocked = false;
				log.debug("The user does not have any vehicles");

			} 

		} catch (DAOException e) {
			throw new ServiceException("Can not unblocked the vehicle");
		}

		return unblocked;

	}

	@Override
	public boolean travelStart(String username, int vehicleId) throws VehicleIsNotAvailable, UserHasAnotherVehicleBlocked, ServiceException {

		VehicleBlocked vehicleBlocked = null;
		Vehicle vehicle = null;
		Boolean start = false;
		Timestamp startTravelingDate = null;

		try {

			vehicleBlocked = vehicleBlockedDAO.getVehicleBlocked(username);

			if (vehicleBlocked == null){  //user dont have any vehicle blocked

				vehicle = vehicleDAO.getVehicle(vehicleId);
				
				if(!vehicle.getRentState().equals(VEHICLE_RENT_UNBLOCK)){ // vehicle is not available

					log.info("User" + username + "can't start traveling because vehicle" + vehicleId + "is already in use");
					log.info("vehicle: " + vehicle.getRentState());
					throw new VehicleIsNotAvailable("Vehicle : " + vehicleId + "is already blocked");

				} else {
					vehicle = vehicleDAO.updateVehicleToTraveling(vehicleId);
					vehicleBlockedDAO.deleteVehicleBlocked(vehicleId);
					startTravelingDate = getTime();
					vehicleTravelingDAO.createVehicleTraveling(username, startTravelingDate, vehicle);
					start = true;
					log.info("User" + username + "start traveling with vehicle" + vehicleId);
					
					return start;
				
				}

			}
			//vehicle and vehicleBlocked are the same | vehicle is available for the user
			if (vehicleBlocked.getVehicle().getId() == vehicleId ){
				vehicle = vehicleDAO.updateVehicleToTraveling(vehicleId);
				vehicleBlockedDAO.deleteVehicleBlocked(vehicleId);
				startTravelingDate = getTime();
				vehicleTravelingDAO.createVehicleTraveling(username, startTravelingDate, vehicle);
				start = true;
				log.info("User" + username + "start traveling with vehicle" + vehicleId);

			} else { //user has another blocked vehicle

				start = false;
				log.debug("the user have another vehicle blocked, so he cant start traveling with this one");
				throw new UserHasAnotherVehicleBlocked("User has another vehicle Blocked");

			}
			
		} catch (DAOException e) {
			throw new ServiceException("Can not unblocked the vehicle");
		}

		return start;

	}

	@Override
	public double travelFinish(String username, int vehicleId) throws ServiceException {

		VehicleTraveling vehicleTraveling = null;
		Vehicle vehicle = null;
		Timestamp finishTravelingDate = null;
		double cost = 0;
		double time = 0;

		try {

			vehicleTraveling = vehicleTravelingDAO.getVehicleTraveling(username);
			vehicle = vehicleDAO.getVehicle(vehicleId);
			finishTravelingDate = getTime();
			cost = travelCost(vehicleTraveling, finishTravelingDate);
			time = travelTime(vehicleTraveling, finishTravelingDate);
			vehicleTravelDAO.createTravel(vehicleTraveling, vehicle, finishTravelingDate, cost, time);
			vehicleTravelingDAO.deleteVehicleTraveling(vehicleId);
			vehicleDAO.updateVehicleToUnblocked(vehicleId);
			log.debug("User" + username + "finish traveling with vehicle" + vehicleId);

		} catch (DAOException e) {
			throw new ServiceException("Can not finishing the travel" + e);
		}

		return cost;

	}

	private Timestamp getTime(){

		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		return timestamp;

	}

	private double travelCost(VehicleTraveling vehicleTraveling, Timestamp finishTravelingDate){
		return round(((finishTravelingDate.getTime() - vehicleTraveling.getDateStart().getTime()) * PRICE_PER_MINUT)/CONVERT_MS_TO_SECONDS ,NUMBER_OF_DECIMALS);
	}

	private  double travelTime(VehicleTraveling vehicleTraveling, Timestamp finishTravelingDate){
		log.debug(finishTravelingDate.getTime() - vehicleTraveling.getDateStart().getTime() + "total time");
		return (finishTravelingDate.getTime() - vehicleTraveling.getDateStart().getTime());
	}

	private double round(double value, int places) {

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);

		return bd.doubleValue();

	}

}
