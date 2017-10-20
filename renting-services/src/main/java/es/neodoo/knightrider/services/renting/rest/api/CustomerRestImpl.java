package es.neodoo.knightrider.services.renting.rest.api;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;
import es.neodoo.knightrider.services.renting.rest.params.ListTravelsResponse;
import es.neodoo.knightrider.services.renting.rest.params.MyProfileResponse;
import es.neodoo.knightrider.services.renting.rest.params.TravelingDetailResponse;
import es.neodoo.knightrider.services.renting.rest.params.UpdateBDResponse;
import es.neodoo.knightrider.services.renting.service.CustomerService;
import es.neodoo.knightrider.services.renting.service.CustomerServiceImpl;
import es.neodoo.knightrider.services.renting.service.KeycloakService;

@Path("/customer")
public class CustomerRestImpl implements CustomerRest {

	private static final Log log = LogFactory.getLog(CustomerRestImpl.class);

	private CustomerService customerService = null;
	
	private KeycloakService keycloakService = null;

	private static final String KEYCLOAK_SERVER = "http://192.168.1.144:9080/auth";
	
	private static final String KEYCLOAK_REALM = "master";

	private static final String KEYCLOAK_CLIENT = "admin-cli";
	
	private static final String KEYCLOAK_ADMIN_USERNAME = "admin";
	
	private static final String KEYCLOAK_ADMIN_PASSWORD = ".admin8$";
	
	private static final String KEYCLOAK_KNIGHTRIDER_REALM = "knightrider_realm";
	
	private static final String KEYCLOAK_KNIGHTRIDER_CLIENT = "knightrider_client";


	public CustomerRestImpl() {
		super();
		this.customerService =  new CustomerServiceImpl();
		this.keycloakService = new KeycloakService(KEYCLOAK_SERVER, KEYCLOAK_REALM, KEYCLOAK_ADMIN_USERNAME, KEYCLOAK_ADMIN_PASSWORD, KEYCLOAK_CLIENT);
	}

	private ResponseBuilder addCorsSupport(ResponseBuilder response){

		return response.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600");
	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.CustomerRest#travelingDetails(java.lang.String)
	 */
	@Override
	@GET
	@Path("/traveling_details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelingDetails(@QueryParam("username") String username) {

		VehicleTraveling vehicleTraveling = null;		
		TravelingDetailResponse travelingDetailResponse = new TravelingDetailResponse();
		String json = null;

		try {

			vehicleTraveling = customerService.getTravelingDetail(username);
			travelingDetailResponse = travelingDetailResponse.buildTravelingDetailResponse(vehicleTraveling);

		} catch (Exception e) {
			travelingDetailResponse = null;
		}		

		try {

			json = travelingDetailResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.CustomerRest#profile(java.lang.String)
	 */
	@Override
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response profile(@QueryParam("username")String username) {

		long numTravels = 0;
		double time = 0;
		double cost = 0;
		double average = 0;
		String name = null;
		MyProfileResponse myProfileResponse = new MyProfileResponse();		
		String json = null;

		try {
			
			name =customerService.getName(username);
			numTravels = customerService.getNumTravels(username);
			time = customerService.getTime(username);
			cost = customerService.getCost(username);
			average = customerService.getAverage(username);
			myProfileResponse = myProfileResponse.buildMyProfileResponse(numTravels, time, cost, average, name);

		} catch (Exception e) {
			myProfileResponse = myProfileResponse.buildMyProfileResponse(numTravels, time, cost, average, name);
			log.error("Eror geting profile:" + e);
		}

		try {

			json = myProfileResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.CustomerRest#travels(java.lang.String)
	 */
	@Override
	@GET
	@Path("/travels")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travels(@QueryParam("username")String username) {

		List<VehicleTravel> travels = null;	
		ListTravelsResponse listTravelsResponse = new ListTravelsResponse();
		String json = null;

		try {

			travels = customerService.getTravels(username);
			listTravelsResponse = listTravelsResponse.buildTravelResponse(travels);

		} catch (Exception e) {
			listTravelsResponse = null;
			log.error("Eror geting travels:" + e);
		}

		try {

			json = listTravelsResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.CustomerRest#register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@QueryParam("email") String email, @QueryParam("password") String pass, @QueryParam("name") String name, @QueryParam("surname") String surname, 
			@QueryParam("birthday") String birthDate, @QueryParam("phone") int phone, @QueryParam("driveNumber") String driveNumber, @QueryParam("driveDate") String driveDate, 
			@QueryParam("creditNumber") String creditCardNumber, @QueryParam("creditCardName") String creditCardName, @QueryParam("creditCardCVS") int creditCardCVS, @QueryParam("creditCardDate") String creditCardDate){

		boolean register = false;	
		UpdateBDResponse registerResponse = new UpdateBDResponse();
		String json = null;
		
		try {
			
			keycloakService.createUser(KEYCLOAK_KNIGHTRIDER_REALM, KEYCLOAK_KNIGHTRIDER_CLIENT, email, pass, name, surname, email);
			register = customerService.createCustomer(email, name, surname, birthDate, phone, driveNumber, driveDate, pass, creditCardNumber, creditCardName, creditCardCVS, creditCardDate);
			registerResponse = registerResponse.buildUpdateBDResponse(register, "You user was created");

		} catch (ServiceException e) {
			registerResponse = registerResponse.buildUpdateBDResponse(false, "Error in the register. Try with other email");
			log.error("Error in the register:" + e);
		} catch (Exception e){
			registerResponse = registerResponse.buildUpdateBDResponse(false, "Error in the register.");
			log.error("Error in the register:" + e);
		}

		try {

			json = registerResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

}
