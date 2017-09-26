package es.neodoo.knightrider.services.renting.rest.api;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.neodoo.knightrider.services.renting.model.vo.Customer;
import es.neodoo.knightrider.services.renting.service.CustomerService;
import es.neodoo.knightrider.services.renting.service.CustomerServiceImpl;

@Path("/customer")
public class CustomerRestImpl implements CustomerRest {
	
	@SuppressWarnings("unused")
	private CustomerService customerService = new CustomerServiceImpl();

	@SuppressWarnings("unused")
	private final static Logger log = Logger.getLogger(Customer.class.getName());
	
	@Override
	@Path("/traveling_details")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response travelingDetails(@QueryParam("username") String username) throws Exception {

		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();

	}
	
	@Override
	@Path("/profile")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response profile(@QueryParam("username")String username) throws Exception {
		
		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();
	}

	@Override
	@Path("/travels")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response travels(@QueryParam("username")String username) throws Exception {
		
		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();

	}

	@Override
	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@QueryParam("email") String email, @QueryParam("password") String pass, @QueryParam("name") String name, @QueryParam("surname") String surname, 
			@QueryParam("birthday") String birth, @QueryParam("phone") int phone, @QueryParam("driveNumber") String driveNumber, @QueryParam("driveDate") String driveDate, 
			@QueryParam("creditNumber") String creditCardNumber, @QueryParam("creditCardName") String creditCardName, @QueryParam("creditCardCVS") int creditCardCVS, @QueryParam("creditCardDate") String creditCardDate){
		
		Response.ResponseBuilder builder = null;

		builder = Response.ok();

		return builder.build();

	}
	
}