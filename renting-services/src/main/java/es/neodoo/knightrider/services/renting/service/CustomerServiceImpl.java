package es.neodoo.knightrider.services.renting.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.model.dao.CustomerCardDAO;
import es.neodoo.knightrider.services.renting.model.dao.CustomerCardDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.CustomerDAO;
import es.neodoo.knightrider.services.renting.model.dao.CustomerDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.UserDAO;
import es.neodoo.knightrider.services.renting.model.dao.UserDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelDAOImpl;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelingDAO;
import es.neodoo.knightrider.services.renting.model.dao.VehicleTravelingDAOImpl;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public class CustomerServiceImpl implements CustomerService {

	private final int NUMBER_OF_DECIMALS = 2;

	CustomerDAO customerDAO = null;

	VehicleTravelDAO vehicleTravelDAO = null;

	UserDAO userDAO = null;
	
	VehicleTravelingDAO vehicleTravelingDAO = null;

	CustomerCardDAO customerCardDAO = null;

	private static final Log log = LogFactory.getLog(CustomerServiceImpl.class);
	
	public CustomerServiceImpl(){
		
		super();
		customerDAO = new CustomerDAOImpl();
		vehicleTravelDAO = new VehicleTravelDAOImpl();
		userDAO = new UserDAOImpl();
		vehicleTravelingDAO = new VehicleTravelingDAOImpl();
		customerCardDAO = new CustomerCardDAOImpl();

	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public boolean createCustomer(String email, String name, String surname, String birthDate, int phone, String driveNumber,
			String driveDate, String pass, String creditCardNumber, String creditCardName, int creditCardCVS, String creditCardDate) throws ServiceException{

		boolean register = false;
		
		try {

			userDAO.createUser(email, pass);
			customerDAO.createCustomer(email, name, surname, getDate("yyyy-mm-dd",birthDate), phone, driveNumber, getDate("yyyy-mm-dd",driveDate));
			customerCardDAO.createCreditCard(email, creditCardNumber, creditCardName, creditCardCVS, getDate("yyyy-mm-dd",creditCardDate));
			register = true;
			log.info( email + "register done");

		} catch(DAOException e) {			
			throw new ServiceException("Can not create customer: " + e);
		} catch (ParseException e) {
			throw new ServiceException("Can not Parse Date: " + e);
		}
		
		return register;

	}
	
	private Date getDate(String dateFormat, String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return (Date) format.parse(date);
	}

	@Override
	public VehicleTraveling getTravelingDetail(String username) throws ServiceException {
		
		VehicleTraveling vehicleTraveling = null;
		
		try {
			
			vehicleTraveling = vehicleTravelingDAO.getVehicleTraveling(username);
			log.debug("Showing traveling details of user" + username);
			
		} catch (DAOException e) {
			log.error("Can not showing the traveling details:" + e);
			throw new ServiceException("Can not show traveling details" + e);
		}
		
		return vehicleTraveling;
		
	}

}

