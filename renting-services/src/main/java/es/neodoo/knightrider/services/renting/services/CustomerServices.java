package es.neodoo.knightrider.services.renting.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.exceptions.ServicesException;
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

	public long getNumTravels(String username) throws ServicesException {

		long numTravels = 0;

		try {

			numTravels = vehicleTravelDAO.countTravels(username);

		} catch(DAOException e){
			throw new ServicesException("Can not get travels count: " + e);
		}

		return numTravels;

	}

	public double getCost(String username) throws ServicesException {

		double cost = 0;

		try {

			cost = round(vehicleTravelDAO.getCost(username), NUMBER_OF_DECIMALS);

		} catch(DAOException e){
			throw new ServicesException("Can not get cost: " + e);
		}

		return cost;

	}

	public double getTime(String username) throws ServicesException {

		double time = 0;

		try {

			time = vehicleTravelDAO.getTime(username);

		} catch(DAOException e){
			throw new ServicesException("Can not get time: " + e);
		}

		return time;

	}

	public double getAverage(String username) throws ServicesException {

		double average = 0;

		try {

			average = getAverage(getNumTravels(username), getCost(username));

		} catch(ServicesException e){
			throw new ServicesException("Can not get average: " + e);
		}

		return average;

	}

	public String getName(String username) throws ServicesException {

		String name = null;

		try {

			name = customerDAO.getName(username);

		} catch(DAOException e) {
			throw new ServicesException("Can not get name: " + e);
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

	public List<VehicleTravel> getTravels(String username) throws ServicesException {

		List<VehicleTravel> travels = null;

		try {

			travels = vehicleTravelDAO.getTravels(username);

		} catch(DAOException e) {
			throw new ServicesException("Can not get travels: " + e);
		}

		return travels;

	}

	public void createCustomer(String email, String name, String surname, Date birthDate, int phone, String driveNumber,
			Date driveDate, String pass, String creditCardNumber, String creditCardName, int creditCardCVS, Date creditCardDate) throws ServicesException{

		try {

			customerDAO.createCustomer(email, name, surname, birthDate, phone, driveNumber, driveDate);
			userDAO.createUser(email, pass);
			customerCardDAO.createCreditCard(email, creditCardNumber, creditCardName, creditCardCVS, creditCardDate);

		} catch(DAOException e) {			
			throw new ServicesException("Can not create customer: " + e);
		}

	}

}

