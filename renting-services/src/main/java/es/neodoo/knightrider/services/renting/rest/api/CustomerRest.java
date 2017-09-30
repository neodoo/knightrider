package es.neodoo.knightrider.services.renting.rest.api;

import javax.ws.rs.core.Response;

public interface CustomerRest {

	Response travelingDetails(String username);

	Response profile(String username);

	Response travels(String username);

	Response register(String email, String pass, String name, String surname, String birthDate, int phone,
			String driveNumber, String driveDate, String creditCardNumber, String creditCardName, int creditCardCVS,
			String creditCardDate);

}