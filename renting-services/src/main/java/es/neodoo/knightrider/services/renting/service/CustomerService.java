package es.neodoo.knightrider.services.renting.service;

import java.util.List;

import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public interface CustomerService {

	long getNumTravels(String username) throws ServiceException;

	double getCost(String username) throws ServiceException;

	double getTime(String username) throws ServiceException;

	double getAverage(String username) throws ServiceException;

	String getName(String username) throws ServiceException;

	List<VehicleTravel> getTravels(String username) throws ServiceException;

	boolean createCustomer(String email, String name, String surname, String birthDate, int phone, String driveNumber,
			String driveDate, String pass, String creditCardNumber, String creditCardName, int creditCardCVS,
			String creditCardDate) throws ServiceException;

	VehicleTraveling getTravelingDetail(String username) throws ServiceException;

}