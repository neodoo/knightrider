package es.neodoo.knightrider.services.renting.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.exceptions.UserHasAnotherVehicleBlocked;
import es.neodoo.knightrider.services.renting.exceptions.VehicleIsNotAvailable;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.rest.params.UpdateBDResponse;
import es.neodoo.knightrider.services.renting.rest.params.VehicleResponse;
import es.neodoo.knightrider.services.renting.service.VehicleService;
import es.neodoo.knightrider.services.renting.service.VehicleServiceImpl;

@Path("/vehicles")
public class VehicleRestImpl implements VehicleRest {

	private static final Log log = LogFactory.getLog(VehicleRestImpl.class);
	
	private VehicleService vehicleService = null;
	
	public VehicleRestImpl() {
		super();
		this.vehicleService =  new VehicleServiceImpl(); ;
	}
	
	private ResponseBuilder addCorsSupport(ResponseBuilder response){
		
		return response.header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .header("Access-Control-Max-Age", "1209600");
	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#test()
	 */
	@Override
	@POST
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {

		String json = "Test succesfull";
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#getVehiclesUnblocked()
	 */
	@Override
	@GET
	@Path("/state/unblocked")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehiclesUnblocked() {

		List<Vehicle> vehiclesWithStatusUnblocked = null;
		VehicleResponse vehicleWithStatusUnblockedResponse =  new VehicleResponse();
		String json = null;

		try {

			vehiclesWithStatusUnblocked  = vehicleService.getVehiclesWithStatusUnblocked();
			vehicleWithStatusUnblockedResponse = vehicleWithStatusUnblockedResponse.buildVehicleResponse(vehiclesWithStatusUnblocked);

		} catch (Exception e) {
			vehicleWithStatusUnblockedResponse.addResponse(null);
			vehicleWithStatusUnblockedResponse.setCount(0);
			log.error("Error geting unblocked vehicles:" + e);
		}

		try {

			json = vehicleWithStatusUnblockedResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();
	            
	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#blocked(java.lang.String)
	 */
	@Override
	@GET
	@Path("/state/blocked")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blocked(@QueryParam("username") String username) {

		Vehicle vehicleWithStatusBlocked = null;
		VehicleResponse vehicleWithStatusBlockedResponse = new VehicleResponse();
		String json = null;

		try {

			vehicleWithStatusBlocked = vehicleService.getVehicleWithStatusBlocked(username);
			vehicleWithStatusBlockedResponse = vehicleWithStatusBlockedResponse.buildVehicleResponse(vehicleWithStatusBlocked);

		} catch (Exception e) {
			vehicleWithStatusBlockedResponse.addResponse(null);
			vehicleWithStatusBlockedResponse.setCount(0);
			log.error("Error geting blocked vehicles:" + e);
		}
		
		try {
			
			json = vehicleWithStatusBlockedResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#travelingVehicles(java.lang.String)
	 */
	@Override
	@GET
	@Path("/state/traveling")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelingVehicles(@QueryParam("username") String username) {

		Vehicle vehicleWithStatusTraveling = null;
		VehicleResponse vehicleWithStatusTravelingResponse = new VehicleResponse();
		String json = null;

		try {

			vehicleWithStatusTraveling = vehicleService.getVehicleWithStatusTraveling(username);
			vehicleWithStatusTravelingResponse = vehicleWithStatusTravelingResponse.buildVehicleResponse(vehicleWithStatusTraveling);

		} catch (Exception e) {
			vehicleWithStatusTravelingResponse.setCount(0);
			vehicleWithStatusTravelingResponse.addResponse(null);
			log.error("Error geting traveling vehicles:" + e);
		}		

		try {

			json = vehicleWithStatusTravelingResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#block(int, java.lang.String)
	 */
	@Override
	@POST
	@Path("/{vehicleId}/block")
	@Produces(MediaType.APPLICATION_JSON)
	public Response block(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {
		log.info("KEYCLOACK TEST");
		Boolean blocked = false;
		UpdateBDResponse blockVehicleResponse = new UpdateBDResponse();
		String json = null;

		try {

			blocked = vehicleService.block(username, vehicleId);
			blockVehicleResponse = blockVehicleResponse.buildUpdateBDResponse(blocked,"Succesful");

		} catch (UserHasAnotherVehicleBlocked | VehicleIsNotAvailable e) {
			blockVehicleResponse = blockVehicleResponse.buildUpdateBDResponse(false , e.getMessage().toString());
			log.error("Error blocking vehicle:" + e);
		} catch (Exception e) {
			blockVehicleResponse = blockVehicleResponse.buildUpdateBDResponse(false , "An error ocurred");
			log.error("Error blocking vehicle:" + e);
		}

		try{

			json = blockVehicleResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#unblock(int, java.lang.String)
	 */
	@Override
	@POST
	@Path("/{vehicleId}/unblock")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unblock(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Boolean blocked = false;
		UpdateBDResponse unblockVehicleResponse = new UpdateBDResponse();
		String json = null;

		try {

			blocked = vehicleService.unblock(username, vehicleId);
			unblockVehicleResponse = unblockVehicleResponse.buildUpdateBDResponse(blocked,"Succesful");

		} catch (ServiceException e) {
			unblockVehicleResponse = unblockVehicleResponse.buildUpdateBDResponse(false,"An error ocurred");
			log.error("Error unblocking vehicle:" + e);
		} catch (Exception e){
			log.error(e);
			log.error("Error unblocking vehicle:" + e);
		}

		try {

			json = unblockVehicleResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#travelStart(int, java.lang.String)
	 */
	@Override
	@POST
	@Path("/{vehicleId}/travel_start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelStart(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Boolean startTravel = false;
		UpdateBDResponse startTravelResponse = new UpdateBDResponse();
		String json = null;

		try {

			startTravel = vehicleService.travelStart(username, vehicleId);
			startTravelResponse = startTravelResponse.buildUpdateBDResponse(startTravel,"Your travel start now");

		} catch (UserHasAnotherVehicleBlocked | VehicleIsNotAvailable e) {
			startTravelResponse = startTravelResponse.buildUpdateBDResponse(false, e.getMessage().toString());
			log.error("Error starting travel:" + e);
		} catch (Exception e){
			startTravelResponse = startTravelResponse.buildUpdateBDResponse(false,"An error ocurred");
			log.error("Error starting travel:" + e);
		}

		try {

			json = startTravelResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.services.renting.rest.api.VehicleRest#travelFinish(int, java.lang.String)
	 */
	@Override
	@POST
	@Path("/{vehicleId}/travel_finish")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelFinish(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		double finishTravelCost = 0;
		UpdateBDResponse startTravelResponse = new UpdateBDResponse();
		String json = null;

		try {

			finishTravelCost = vehicleService.travelFinish(username, vehicleId);
			startTravelResponse = startTravelResponse.buildUpdateBDResponse(true, String.valueOf(finishTravelCost));

		}  catch (Exception e){
			startTravelResponse = startTravelResponse.buildUpdateBDResponse(false,"An error ocurred");
			log.error("Error finishing travel:" + e);
		}

		try {

			json = startTravelResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

}
