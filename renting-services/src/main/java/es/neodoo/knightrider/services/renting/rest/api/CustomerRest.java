package es.neodoo.knightrider.services.renting.rest.api;

import javax.ws.rs.core.Response;

public interface CustomerRest {

	Response travelingDetails(String username) throws Exception;

	Response profile(String username) throws Exception;

	Response travels(String username) throws Exception;

	Response register(String email, String pass, String name, String surname, String birth, int phone,
			String driveNumber, String driveDate, String creditCardNumber, String creditCardName, int creditCardCVS,
			String creditCardDate);

}