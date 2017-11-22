package es.neodoo.knightrider.car.services.rest.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.neodoo.knightrider.car.services.exceptions.*;
import es.neodoo.knightrider.car.services.mqtt.Publisher;
import es.neodoo.knightrider.car.services.rest.params.*;
import es.neodoo.knightrider.car.services.simulation.hardware.VehicleHardwareInvoker;

@Path("/vehicle")
@Produces("application/json")
public class VehiclesCommandRestImpl implements VehiclesCommandRest {

	private static final Log log = LogFactory.getLog(VehicleHardwareInvoker.class);
	
	private VehicleHardwareInvoker vh = null;
	
	public VehiclesCommandRestImpl(){
		super();
		this.vh = new VehicleHardwareInvoker();	
	}
	
private ResponseBuilder addCorsSupport(ResponseBuilder response){
		
		return response.header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .header("Access-Control-Max-Age", "1209600");
	}
	
	
	@Override
	@Path("/{vehicleId}/wake_up")
	@POST
	public Response wakeUpCar(@PathParam("vehicleId") int vehicleId) {

		boolean wakeUpCar = false;
		WakeUpCarResponse wakeUpCarResponse = new WakeUpCarResponse();
		String json = null;

		try {

			wakeUpCar = vh.wakeUpCar(vehicleId);
			wakeUpCarResponse = wakeUpCarResponse.buildWakeUpResponse(wakeUpCar, "Vehicle wake up");

		} catch (VehicleHardwareInvokerException e) {
			wakeUpCarResponse.buildWakeUpResponse(false, e.toString());
			log.error("Error waking up vehicle:" + e);
		}
		
		try {
			
			json = wakeUpCarResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/set_valet_mode")
	@POST
	public Response setValetMode(@PathParam("vehicleId") int vehicleId, @QueryParam("onoff")Boolean on, @QueryParam("pin") int pin) {

		boolean setValetMode = false;
		SetValetModeResponse setValetModeResponse = new SetValetModeResponse();
		String json = null;

		try {

			setValetMode = vh.setValetMode(vehicleId, on, pin);
			setValetModeResponse = setValetModeResponse.buildSetValetModeResponse(setValetMode, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			setValetModeResponse.buildSetValetModeResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = setValetModeResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/reset_valet_pin")
	@POST
	public Response resetValetPin(@PathParam("vehicleId") int vehicleId) {

		boolean resetValetPin = false;
		ResetValetPinResponse resetValetPinResponse = new ResetValetPinResponse();
		String json = null;

		try {

			resetValetPin = vh.resetValetPin(vehicleId);
			resetValetPinResponse = resetValetPinResponse.buildResetValetPinResponse(resetValetPin, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			resetValetPinResponse.buildResetValetPinResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = resetValetPinResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@POST
	@Path("/{vehicleId}/command/charge_port_door_open")
	public Response openChargePort(@PathParam("vehicleId") int vehicleId) {

		boolean openChargePort = false;
		OpenChargePortResponse openChargePortResponse = new OpenChargePortResponse();
		String json = null;

		try {

			openChargePort = vh.openChargeDoor(vehicleId);
			openChargePortResponse = openChargePortResponse.buildOpenChargePortResponse(openChargePort, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			openChargePortResponse.buildOpenChargePortResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = openChargePortResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();
	}
	
	@Override
	@Path("/{vehicleId}/command/charge_standard")
	@POST
	public Response setChargeLimitToStandard(@PathParam("vehicleId") int vehicleId) {

		boolean setChargeLimitToStandard = false;
		SetChargeLimitToStandardResponse setChargeLimitToStandardResponse = new SetChargeLimitToStandardResponse();
		String json = null;

		try {

			setChargeLimitToStandard = vh.setChargeLimitToStandard(vehicleId);
			setChargeLimitToStandardResponse = setChargeLimitToStandardResponse.buildSetChargeLimitToStandardResponse(setChargeLimitToStandard, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			setChargeLimitToStandardResponse.buildSetChargeLimitToStandardResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = setChargeLimitToStandardResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/charge_max_range")
	@POST
	public Response setChargeLimitToMaxRange(@PathParam("vehicleId") int vehicleId) {

		boolean setChargeLimitToMaxRange = false;
		SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponse = new SetChargeLimitToMaxRangeResponse();
		String json = null;

		try {

			setChargeLimitToMaxRange = vh.setChargeLimitToMaxRange(vehicleId);
			setChargeLimitToMaxRangeResponse = setChargeLimitToMaxRangeResponse.buildSetChargeLimitToMaxRangeResponse(setChargeLimitToMaxRange, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			setChargeLimitToMaxRangeResponse.buildSetChargeLimitToMaxRangeResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = setChargeLimitToMaxRangeResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/set_charge_limit")
	@POST
	public Response setChargeLimit(@PathParam("vehicleId") int vehicleId, @QueryParam("percent")int percent) {

		boolean setChargeLimit = false;
		SetChargeLimitResponse setChargeLimitResponse = new SetChargeLimitResponse();
		String json = null;

		try {

			setChargeLimit = vh.setChargeLimit(vehicleId , percent);
			setChargeLimitResponse = setChargeLimitResponse.buildSetChargeLimitResponse(setChargeLimit, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			setChargeLimitResponse.buildSetChargeLimitResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = setChargeLimitResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/charge_start")
	@POST
	public Response startCharging(@PathParam("vehicleId") int vehicleId) {

		boolean startCharging = false;
		StartChargingResponse startChargingResponse = new StartChargingResponse();
		String json = null;

		try {

			startCharging = vh.startCharging(vehicleId);
			startChargingResponse = startChargingResponse.buildStartChargingResponse(startCharging, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			startChargingResponse.buildStartChargingResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = startChargingResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/charge_stop")
	@POST
	public Response stopCharging(@PathParam("vehicleId") int vehicleId) {

		boolean stopCharging = false;
		StopChargingResponse stopChargingResponse = new StopChargingResponse();
		String json = null;

		try {

			stopCharging = vh.stopCharging(vehicleId);
			stopChargingResponse = stopChargingResponse.buildStopChargingResponse(stopCharging, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			stopChargingResponse.buildStopChargingResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = stopChargingResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();
		
	}

	@Override
	@Path("/{vehicleId}/command/flash_lights")
	@POST
	public Response flashLights(@PathParam("vehicleId") int vehicleId) {

		boolean flashLights = false;
		FlashLightsResponse flashLightsResponse = new FlashLightsResponse();
		String json = null;

		try {

			flashLights = vh.stopCharging(vehicleId);
			flashLightsResponse = flashLightsResponse.buildFlashLightsResponse(flashLights, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			flashLightsResponse.buildFlashLightsResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = flashLightsResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	/* (non-Javadoc)
	 * @see es.neodoo.knightrider.car.services.rest.api.VehiclesCommandRest#honkHorn(int)
	 */
	@Override
	@Path("/{vehicleId}/command/honk_horn")
	@POST
	public Response honkHorn(@PathParam("vehicleId") int vehicleId) {

		boolean honkHorn = false;
		HonkHornResponse honkHornResponse = new HonkHornResponse();
		String json = null;

		try {

			honkHorn = vh.stopCharging(vehicleId);
			honkHornResponse = honkHornResponse.buildHonkHornResponse(honkHorn, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			honkHornResponse.buildHonkHornResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = honkHornResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();
		
	}

	@Override
	@Path("/{vehicleId}/command/door_unlock")
	@POST
	public Response unlockDoors(@PathParam("vehicleId") int vehicleId) {
		
		boolean unlockDoors = false;
		UnlockDoorsResponse unlockDoorsResponse = new UnlockDoorsResponse();
		String json = null;

		try {
			unlockDoors = vh.unlockDoors(vehicleId);
			unlockDoorsResponse = unlockDoorsResponse.buildUnlockDoorsResponse(unlockDoors, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			unlockDoorsResponse.buildUnlockDoorsResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = unlockDoorsResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();
		
	}
	
	@Override
	@Path("/{vehicleId}/command/door_lock")
	@POST
	public Response lockDoors(@PathParam("vehicleId") int vehicleId) {

		boolean lockDoors = false;
		LockDoorsResponse lockDoorsResponse = new LockDoorsResponse();
		String json = null;

		try {

			lockDoors = vh.stopCharging(vehicleId);
			lockDoorsResponse = lockDoorsResponse.buildLockDoorsResponse(lockDoors, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			lockDoorsResponse.buildLockDoorsResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = lockDoorsResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}

	@Override
	@Path("/{vehicleId}/command/set_temps")
	@POST
	public Response setTemperature(@PathParam("vehicleId") int vehicleId, @QueryParam("driver_temp")double driverTemp, @QueryParam("passenger_temp") double passengerTemp) {

		boolean setTemperature = false;
		SetTemperatureResponse setTemperatureResponse = new SetTemperatureResponse();
		String json = null;

		try {

			setTemperature = vh.setTemperature(vehicleId, driverTemp, passengerTemp);
			setTemperatureResponse = setTemperatureResponse.buildSetTemperatureResponse(setTemperature, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			setTemperatureResponse.buildSetTemperatureResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = setTemperatureResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/auto_conditioning_start")
	@POST
	public Response startHvacSystem(@PathParam("vehicleId") int vehicleId) {

		boolean startHvacSystem = false;
		StartHvacSystemResponse startHvacSystemResponse = new StartHvacSystemResponse();
		String json = null;

		try {

			startHvacSystem = vh.stopCharging(vehicleId);
			startHvacSystemResponse = startHvacSystemResponse.buildStartHvacSystemResponse(startHvacSystem, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			startHvacSystemResponse.buildStartHvacSystemResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = startHvacSystemResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/auto_conditioning_stop")
	@POST
	public Response stopHvacSystem(@PathParam("vehicleId") int vehicleId) throws VehicleHardwareInvokerException {

		boolean stopHvacSystem = false;
		StopHvacSystemResponse stopHvacSystemResponse = new StopHvacSystemResponse();
		String json = null;

		try {

			stopHvacSystem = vh.stopCharging(vehicleId);
			stopHvacSystemResponse = stopHvacSystemResponse.buildStopHvacSystemResponse(stopHvacSystem, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			stopHvacSystemResponse.buildStopHvacSystemResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = stopHvacSystemResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/sun_roof_control")
	@POST
	public Response MovePanoRoof(@PathParam("vehicleId") int vehicleId, @QueryParam("state")String state, @QueryParam("percent") int percent) throws VehicleHardwareInvokerException {

		boolean movePanoRoof = false;
		MovePanoRoofResponse movePanoRoofResponse = new MovePanoRoofResponse();
		String json = null;

		try {

			movePanoRoof = vh.movePanoRoof(vehicleId, state,percent);
			movePanoRoofResponse = movePanoRoofResponse.buildMovePanoRoofResponse(movePanoRoof, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			movePanoRoofResponse.buildMovePanoRoofResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = movePanoRoofResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/remote_start_drive")
	@POST
	public Response remoteStart(@PathParam("vehicleId") int vehicleId, @QueryParam("password")String password) throws VehicleHardwareInvokerException {

		boolean remoteStart = false;
		RemoteStartResponse remoteStartResponse = new RemoteStartResponse();
		String json = null;

		try {

			remoteStart = vh.stopCharging(vehicleId);
			remoteStartResponse = remoteStartResponse.buildRemoteStartResponse(remoteStart, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			remoteStartResponse.buildRemoteStartResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = remoteStartResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();

	}
	
	@Override
	@Path("/{vehicleId}/command/trunk_open")
	@POST
	public Response openTrunk(@PathParam("vehicleId") int vehicleId, @QueryParam("which")String which) throws VehicleHardwareInvokerException {
	
		boolean openTrunk = false;
		OpenTrunkResponse openTrunkResponse = new OpenTrunkResponse();
		String json = null;

		try {

			openTrunk = vh.stopCharging(vehicleId);
			openTrunkResponse = openTrunkResponse.buildOpenTrunkResponse(openTrunk, "Succesfull");

		} catch (VehicleHardwareInvokerException e) {
			openTrunkResponse.buildOpenTrunkResponse(false, e.toString());
			log.error("Error setting valet mode vehicle:" + e);
		}
		
		try {
			
			json = openTrunkResponse.toJson();
			
		} catch (JsonProcessingException e) {
			log.error("Eror converting Response to Json:" + e);
		}
		
		return  addCorsSupport(Response.status(200)).entity(json).build();
		
	}

}
