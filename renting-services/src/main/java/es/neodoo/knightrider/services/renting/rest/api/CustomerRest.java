package es.neodoo.knightrider.services.renting.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

public class CustomerRest {

	private static final Log log = LogFactory.getLog(CustomerRest.class);


	CustomerService customerService = new CustomerServiceImpl();
	TravelingDetailResponse travelingDetailResponse = new TravelingDetailResponse();
	MyProfileResponse myProfileResponse = new MyProfileResponse();
	UpdateBDResponse updateBDResponse = new UpdateBDResponse();
	ListTravelsResponse listTravelsResponse = new ListTravelsResponse();

	@GET
	@Path("/traveling_details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelingDetails(@QueryParam("username") String username) {

		VehicleTraveling vehicleTraveling = null;		
		TravelingDetailResponse travelingDetailResponse = null;
		String json = null;

		try {

			vehicleTraveling = customerService.getTravelingDetail(username);
			travelingDetailResponse = this.travelingDetailResponse.buildTravelingDetailResponse(vehicleTraveling);

		} catch (Exception e) {
			travelingDetailResponse = null;
		}		

		try {

			json = travelingDetailResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response profile(@QueryParam("username")String username) {

		long numTravels = 0;
		double time = 0;
		double cost = 0;
		double average = 0;
		String name = null;
		MyProfileResponse myProfileResponse = null;		
		String json = null;

		try {

			numTravels = customerService.getNumTravels(username);
			time = customerService.getTime(username);
			cost = customerService.getCost(username);
			average = customerService.getAverage(username);
			name =customerService.getName(username);
			myProfileResponse = this.myProfileResponse.buildMyProfileResponse(numTravels, time, cost, average, name);

		} catch (Exception e) {
			myProfileResponse = null;
			log.error("Eror geting profile:" + e);
		}

		try {

			json = myProfileResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@GET
	@Path("/travels")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travels(@QueryParam("username")String username) {

		List<VehicleTravel> travels = null;	
		ListTravelsResponse listTravelsResponse = null;
		String json = null;

		try {

			travels = customerService.getTravels(username);
			listTravelsResponse = this.listTravelsResponse.buildTravelResponse(travels);

		} catch (Exception e) {
			listTravelsResponse = null;
			log.error("Eror geting travels:" + e);
		}

		try {

			json = listTravelsResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@QueryParam("email") String email, @QueryParam("password") String pass, @QueryParam("name") String name, @QueryParam("surname") String surname, 
			@QueryParam("birthday") String birthDate, @QueryParam("phone") int phone, @QueryParam("driveNumber") String driveNumber, @QueryParam("driveDate") String driveDate, 
			@QueryParam("creditNumber") String creditCardNumber, @QueryParam("creditCardName") String creditCardName, @QueryParam("creditCardCVS") int creditCardCVS, @QueryParam("creditCardDate") String creditCardDate){

		boolean register = false;	
		UpdateBDResponse registerResponse = null;
		String json = null;

		try {

			register = customerService.createCustomer(email, name, surname, birthDate, phone, driveNumber, driveDate, pass, creditCardNumber, creditCardName, creditCardCVS, creditCardDate);
			registerResponse = updateBDResponse.buildUpdateBDResponse(register, "You user was created");

		} catch (ServiceException e) {
			registerResponse = updateBDResponse.buildUpdateBDResponse(false, "Error in the register. Try with other email");
			log.error("Error in the register:" + e);
		} catch (Exception e){
			registerResponse = updateBDResponse.buildUpdateBDResponse(false, "Error in the register.");
		}

		try {

			json = registerResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();
	}
}
