package es.neodoo.knightrider.services.renting.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.model.dao.CustomerCardDAO;
import es.neodoo.knightrider.services.renting.model.dao.CustomerDAO;
import es.neodoo.knightrider.services.renting.model.dao.UserDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelDAO;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;

public class CustomerServices {

	private final int NUMBER_OF_DECIMALS = 2;

	CustomerDAO customerDAO;

	VehicleTravelDAO vehicleTravelDAO;

	UserDAO userDAO;

	CustomerCardDAO customerCardDAO;

	private static final Log log = LogFactory.getLog(CustomerServices.class);

	public long getNumTravels(String username) throws ServiceException {

		long numTravels = 0;

		try {

			numTravels = vehicleTravelDAO.countTravels(username);
			log.info("Count travels:" + numTravels);

		} catch(DAOException e){
			throw new ServiceException("Can not get travels count: " + e);
		}

		return numTravels;

	}

	public double getCost(String username) throws ServiceException {

		double cost = 0;

		try {

			cost = round(vehicleTravelDAO.getCost(username), NUMBER_OF_DECIMALS);
			log.info("total cost:" + cost);

		} catch(DAOException e){
			throw new ServiceException("Can not get cost: " + e);
		}

		return cost;

	}

	public double getTime(String username) throws ServiceException {

		double time = 0;

		try {

			time = vehicleTravelDAO.getTime(username);
			log.info("total time:" + time);

		} catch(DAOException e){
			throw new ServiceException("Can not get time: " + e);
		}

		return time;

	}

	public double getAverage(String username) throws ServiceException {

		double average = 0;

		try {

			average = getAverage(getNumTravels(username), getCost(username));
			log.info("average nummber of travels and total cost:" + average);

		} catch(ServiceException e){
			throw new ServiceException("Can not get average: " + e);
		}

		return average;

	}

	public String getName(String username) throws ServiceException {

		String name = null;

		try {

			name = customerDAO.getName(username);
			log.info("User's name:" + name);

		} catch(DAOException e) {
			throw new ServiceException("Can not get name: " + e);
		}

		return name;

	}

	private double getAverage(long num_travels, double cost) {
		return round(cost/num_travels, NUMBER_OF_DECIMALS);
	}

	private double round(double value, int places) {

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);

		return bd.doubleValue();

	}

	public List<VehicleTravel> getTravels(String username) throws ServiceException {

		List<VehicleTravel> travels = null;

		try {

			travels = vehicleTravelDAO.getTravels(username);
			log.info("User's travels list:" + travels.toString());

		} catch(DAOException e) {
			throw new ServiceException("Can not get travels: " + e);
		}

		return travels;

	}

	public void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber,
			Date driveDate, String pass, String creditCardNumber, String creditCardName, int creditCardCVS, Date creditCardDate) throws ServiceException{

		try {

			userDAO.createUser(email, pass);
			customerDAO.createCustomer(email, name, surname, birthDate, phone, driveNumber, driveDate);
			customerCardDAO.createCreditCard(email, creditCardNumber, creditCardName, creditCardCVS, creditCardDate);
			log.info( email + "register done");

		} catch(DAOException e) {			
			throw new ServiceException("Can not create customer: " + e);
		}

	}

}

