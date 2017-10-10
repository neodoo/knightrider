package es.neodoo.knightrider.car.services.rest.api;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.neodoo.knightrider.car.services.exceptions.*;
import es.neodoo.knightrider.car.services.rest.params.*;
import es.neodoo.knightrider.car.services.simulation.cloud.CloudServiceInvoker;
import es.neodoo.knightrider.car.services.simulation.hardware.VehicleHardwareInvoker;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
public class VehiclesRestImpl implements VehiclesRest {

	private static final Log log = LogFactory.getLog(VehicleHardwareInvoker.class);

	private VehicleHardwareInvoker vh = null;

	private CloudServiceInvoker cs = null;

	public VehiclesRestImpl() {
		super();
		this.vh =  new VehicleHardwareInvoker();
		this.cs = new CloudServiceInvoker();
	}

	private ResponseBuilder addCorsSupport(ResponseBuilder response){

		return response.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600");
	}

	@Override
	@GET
	public Response listVehicles() throws VehicleHardwareInvokerException {

		String json = null;

		try {

			ListVehiclesResponse vehiclesResponse = null;
			vehiclesResponse = cs.listVehicles();
			json = vehiclesResponse.toJson();

		} catch (IOException e) {
			log.error("Error invoking Tesla API : " + e.getMessage());
		}

		return Response.status(200).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/mobile_enabled")
	@GET
	public Response mobileAcces(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException  {

		boolean mobileAcces = false;
		MobileAccesResponse mobileAccesResponse = new MobileAccesResponse();
		String json = null;

		try {

			mobileAcces = vh.wakeUpCar(vehicleId);
			mobileAccesResponse = mobileAccesResponse.buildMobileAccesResponse(mobileAcces);

		} catch (VehicleHardwareInvokerException e) {
			mobileAccesResponse.buildMobileAccesResponse(false);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = mobileAccesResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/data_request/charge_state")
	@GET
	public Response chargeState(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		String chargeStateParams = null;
		ChargeStateResponse chargeStateResponse = new ChargeStateResponse();
		String json = null;

		try {

			chargeStateParams = vh.chargeState(vehicleId);
			chargeStateResponse = chargeStateResponse.buildChargeStateResponse(chargeStateParams);

		} catch (VehicleHardwareInvokerException e) {
			chargeStateResponse.setResponse(null);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = chargeStateResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/data_request/climate_state")
	@GET
	public Response climateSettings(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		Double climateSettingsParams = null;
		ClimateSettingsResponse climateSettingsResponse = new ClimateSettingsResponse();
		String json = null;

		try {

			climateSettingsParams = vh.climateSettings(vehicleId);
			climateSettingsResponse = climateSettingsResponse.buildClimateSettingsResponse(climateSettingsParams);

		} catch (VehicleHardwareInvokerException e) {
			climateSettingsResponse.setResponse(null);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = climateSettingsResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/data_request/drive_state")
	@GET
	public Response drivingAndPosition(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		String drivingAndPositionParams = null;
		DrivingAndPositionResponse drivingAndPositionResponse = new DrivingAndPositionResponse();
		String json = null;

		try {

			drivingAndPositionParams = vh.drivingAndPositionState(vehicleId);
			drivingAndPositionResponse = drivingAndPositionResponse.buildDrivingAndPositionResponse(drivingAndPositionParams);

		} catch (VehicleHardwareInvokerException e) {
			drivingAndPositionResponse.setResponse(null);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = drivingAndPositionResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/data_request/gui_settings")
	@GET
	public Response guiSettings(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		String guiSettingsParams = null;
		GuiSettingsResponse guiSettingsResponse = new GuiSettingsResponse();
		String json = null;

		try {

			guiSettingsParams = vh.guiSettings(vehicleId);
			guiSettingsResponse = guiSettingsResponse.buildGuiSettingsResponse(guiSettingsParams);

		} catch (VehicleHardwareInvokerException e) {
			guiSettingsResponse.setResponse(null);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = guiSettingsResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/data_request/vehicle_state")
	@GET
	public Response vehicleState(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		boolean vehicleStateParams = false;
		VehicleStateResponse vehicleStateResponse = new VehicleStateResponse();
		String json = null;

		try {

			vehicleStateParams = vh.vehicleState(vehicleId);
			vehicleStateResponse = vehicleStateResponse.buildVehicleStateResponse(vehicleStateParams);

		} catch (VehicleHardwareInvokerException e) {
			vehicleStateResponse.setResponse(null);
			log.error("Error waking up vehicle:" + e);
		}

		try {

			json = vehicleStateResponse.toJson();

		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}

		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

}
