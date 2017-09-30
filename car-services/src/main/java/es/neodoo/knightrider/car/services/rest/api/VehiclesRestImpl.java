package es.neodoo.knightrider.car.services.rest.api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.neodoo.knightrider.car.services.exceptions.*;
import es.neodoo.knightrider.car.services.rest.params.*;
import es.neodoo.knightrider.car.services.simulation.cloud.CloudServiceInvoker;
import es.neodoo.knightrider.car.services.simulation.hardware.VehicleHardwareInvoker;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
public class VehiclesRestImpl implements VehiclesRest {

	private final static Logger log = Logger.getLogger(VehiclesRestImpl.class.getName());

	@Override
	@GET
	public Response listVehicles() throws TeslaInvokerException {

		String json = null;

		try {
			
			ListVehiclesResponse vehiclesResponse = null;
			CloudServiceInvoker csi = new CloudServiceInvoker();
			vehiclesResponse = csi.listVehicles();
			json = vehiclesResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

	@Override
	@Path("/{vehicleId}/mobile_enabled")
	@GET
	public Response mobileAcces(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException  {

		String json = null;

		try {
			
			MobileAccesResponse mobileAccesResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			mobileAccesResponse = vh.mobileAcces(vehicleId);
			json = mobileAccesResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

	@Override
	@Path("/{vehicleId}/data_request/charge_state")
	@GET
	public Response chargeState(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException {

		String json = null;

		try {
			
			ChargeStateResponse chargeStateResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			chargeStateResponse = vh.chargeState(vehicleId);
			json = chargeStateResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

	@Override
	@Path("/{vehicleId}/data_request/climate_state")
	@GET
	public Response climateSettings(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException {

		String json = null;

		try {
			
			ClimateSettingsResponse climateSettingsResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			climateSettingsResponse = vh.climateState(vehicleId);
			json = climateSettingsResponse.toJson();

		}catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

	@Override
	@Path("/{vehicleId}/data_request/drive_state")
	@GET
	public Response drivingAndPosition(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException {

		String json = null;

		try {
			
			DrivingAndPositionResponse drivingAndPositionResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			drivingAndPositionResponse = vh.drivingAndPositionState(vehicleId);
			json = drivingAndPositionResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}
	
	@Override
	@Path("/{vehicleId}/data_request/gui_settings")
	@GET
	public Response guiSettings(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException {
		
		String json = null;
		
		try {
			
			GuiSettingsResponse guiSettingsResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			guiSettingsResponse = vh.guiSetting(vehicleId);
			json = guiSettingsResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

	@Override
	@Path("/{vehicleId}/data_request/vehicle_state")
	@GET
	public Response vehicleState(@PathParam("vehicleId") int vehicleId) throws TeslaInvokerException {

		String json = null;

		try {
			
			VehicleStateResponse vehicleStateResponse = null;
			VehicleHardwareInvoker vh = new VehicleHardwareInvoker();
			vehicleStateResponse = vh.vehicleState(vehicleId);
			json = vehicleStateResponse.toJson();

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error invoking Tesla API : " + e.getMessage());
		}
		
		return Response.status(200).entity(json).build();
	
	}

}
