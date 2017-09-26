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
public class VehicleRestImpl implements VehicleRest {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(VehicleRestImpl.class);
	
	VehicleService vehicleService = new VehicleServiceImpl(); 

	@Override
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();

	}
	
	@Override
	@GET
	@Path("/state/unblocked")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehiclesUnblocked() {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	}
	
	@Override
	@GET
	@Path("/state/blocked")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public Response blocked(@QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	
	}
	
	@Override
	@GET
	@Path("/state/traveling")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public Response travelingVehicles(@QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	
	}

	@Override
	@POST
	@Path("/{vehicleId}/block")
	@Produces(MediaType.APPLICATION_JSON)
	public Response block(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
		
	}
	
	@Override
	@POST
	@Path("/{vehicleId}/unblock")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unblock(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	
	}
	
	@Override
	@POST
	@Path("/{vehicleId}/travel_start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelStart(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	
	}
	

	@Override
	@POST
	@Path("/{vehicleId}/travel_finish")
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelFinish(@PathParam("vehicleId") int vehicleId, @QueryParam("username") String username) {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	
	}

}
