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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.neodoo.knightrider.services.renting.exceptions.ServiceException;
import es.neodoo.knightrider.services.renting.exceptions.UserHasAnotherVehicleBlocked;
import es.neodoo.knightrider.services.renting.exceptions.VehicleIsNotAvailable;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.rest.params.UpdateBDParamResponse;
import es.neodoo.knightrider.services.renting.rest.params.UpdateBDResponse;
import es.neodoo.knightrider.services.renting.rest.params.VehicleParamResponse;
import es.neodoo.knightrider.services.renting.rest.params.VehicleResponse;
import es.neodoo.knightrider.services.renting.service.VehicleService;
import es.neodoo.knightrider.services.renting.service.VehicleServiceImpl;

@Path("/vehicles")
public class VehicleRest {

	private static final Log log = LogFactory.getLog(VehicleRest.class);

	VehicleService vehicleService = new VehicleServiceImpl();
	VehicleResponse vehicleResponse = new VehicleResponse();
	UpdateBDResponse updateBDResponse = new UpdateBDResponse();

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();

	}

	@GET
	@Path("/state/unblocked")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehiclesUnblocked() {

		List<Vehicle> vehiclesWithStatusUnblocked = null;
		VehicleResponse vehicleWithStatusUnblockedResponse = null;
		String json = null;

		try {

			vehiclesWithStatusUnblocked  = vehicleService.getVehiclesWithStatusUnblocked();
			vehicleWithStatusUnblockedResponse = vehicleResponse.buildVehicleResponse(vehiclesWithStatusUnblocked);

		} catch (Exception e) {
			vehicleWithStatusUnblockedResponse = null;
			log.error("Error geting unblocked vehicles:" + e);
		}

		try {

			json = vehicleWithStatusUnblockedResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@GET
	@Path("/state/blocked")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blocked(@QueryParam("username") String username) {

		Vehicle vehicleWithStatusBlocked = null;
		VehicleResponse vehicleWithStatusBlockedResponse = null;
		String json = null;

		try {

			vehicleWithStatusBlocked = vehicleService.getVehicleWithStatusBlocked(username);
			vehicleWithStatusBlockedResponse = vehicleResponse.buildVehicleResponse(vehicleWithStatusBlocked);

		} catch (Exception e) {
			vehicleWithStatusBlockedResponse = null;
			log.error("Error geting blocked vehicles:" + e);
		}
		try {
			json = vehicleWithStatusBlockedResponse.toJson();
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		return Response.status(200).entity(json).build();

	}

	@GET
	@Path("/state/traveling")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelingVehicles(@QueryParam("username") String username) {

		Vehicle vehicleWithStatusTraveling = null;
		VehicleResponse vehicleWithStatusTravelingResponse = null;
		String json = null;

		try {

			vehicleWithStatusTraveling = vehicleService.getVehicleWithStatusTraveling(username);
			vehicleWithStatusTravelingResponse = vehicleResponse.buildVehicleResponse(vehicleWithStatusTraveling);

		} catch (Exception e) {
			vehicleWithStatusTravelingResponse = null;
			log.error("Error geting traveling vehicles:" + e);
		}		

		try {

			json = vehicleWithStatusTravelingResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/{vehicleId}/block")
	@Produces(MediaType.APPLICATION_JSON)
	public Response block(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Boolean blocked = false;
		UpdateBDResponse blockVehicleResponse = null;

		String json = null;

		try {

			blocked = vehicleService.block(username, vehicleId);
			blockVehicleResponse = updateBDResponse.buildUpdateBDResponse(blocked,"Succesful");

		} catch (UserHasAnotherVehicleBlocked | VehicleIsNotAvailable e) {
			blockVehicleResponse = updateBDResponse.buildUpdateBDResponse(false , e.getMessage().toString());
			log.error("Error blocking vehicle:" + e);
		} catch (Exception e) {
			blockVehicleResponse = updateBDResponse.buildUpdateBDResponse(false , "An error ocurred");
			log.error("Error blocking vehicle:" + e);
		}

		try{

			json = blockVehicleResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/{vehicleId}/unblock")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unblock(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Boolean blocked = false;
		UpdateBDResponse unblockVehicleResponse = null;

		String json = null;

		try {

			blocked = vehicleService.unblock(username, vehicleId);
			unblockVehicleResponse = updateBDResponse.buildUpdateBDResponse(blocked,"Succesful");

		} catch (ServiceException e) {
			unblockVehicleResponse = updateBDResponse.buildUpdateBDResponse(false,"An error ocurred");
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

		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/{vehicleId}/travel_start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelStart(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Boolean startTravel = false;
		UpdateBDResponse startTravelResponse = null;

		String json = null;

		try {

			startTravel = vehicleService.travelStart(username, vehicleId);
			startTravelResponse = updateBDResponse.buildUpdateBDResponse(startTravel,"Your travel start now");

		} catch (UserHasAnotherVehicleBlocked | VehicleIsNotAvailable e) {
			startTravelResponse = updateBDResponse.buildUpdateBDResponse(false, e.getMessage().toString());
			log.error("Error starting travel:" + e);
		} catch (Exception e){
			startTravelResponse = updateBDResponse.buildUpdateBDResponse(false,"An error ocurred");
			log.error("Error starting travel:" + e);
		}

		try {

			json = startTravelResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/{vehicleId}/travel_finish")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelFinish(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		double finishTravelCost = 0;
		UpdateBDResponse startTravelResponse = null;

		String json = null;

		try {

			finishTravelCost = vehicleService.travelFinish(username, vehicleId);
			startTravelResponse = updateBDResponse.buildUpdateBDResponse(true, String.valueOf(finishTravelCost));

		}  catch (Exception e){
			startTravelResponse = updateBDResponse.buildUpdateBDResponse(false,"An error ocurred");
			log.error("Error finishing travel:" + e);
		}

		try {

			json = startTravelResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return Response.status(200).entity(json).build();

	}

}
