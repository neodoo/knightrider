package es.neodoo.knightrider.services.renting.rest.api;

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

@Path("/vehicles")
public class VehicleRestImpl implements VehicleRest {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(VehicleRestImpl.class);
	
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
