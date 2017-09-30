package es.neodoo.knightrider.car.services.simulation.hardware;

import es.neodoo.knightrider.car.services.rest.params.ChargeStateParamResponse;
import es.neodoo.knightrider.car.services.rest.params.ChargeStateResponse;
import es.neodoo.knightrider.car.services.rest.params.ClimateSettingsParamResponse;
import es.neodoo.knightrider.car.services.rest.params.ClimateSettingsResponse;
import es.neodoo.knightrider.car.services.rest.params.DrivingAndPositionParamResponse;
import es.neodoo.knightrider.car.services.rest.params.DrivingAndPositionResponse;
import es.neodoo.knightrider.car.services.rest.params.FlashLightsResponse;
import es.neodoo.knightrider.car.services.rest.params.GuiSettingsParamResponse;
import es.neodoo.knightrider.car.services.rest.params.GuiSettingsResponse;
import es.neodoo.knightrider.car.services.rest.params.HonkHornResponse;
import es.neodoo.knightrider.car.services.rest.params.LockDoorsResponse;
import es.neodoo.knightrider.car.services.rest.params.MobileAccesResponse;
import es.neodoo.knightrider.car.services.rest.params.MovePanoRoofResponse;
import es.neodoo.knightrider.car.services.rest.params.OpenChargePortResponse;
import es.neodoo.knightrider.car.services.rest.params.OpenTrunkResponse;
import es.neodoo.knightrider.car.services.rest.params.RemoteStartResponse;
import es.neodoo.knightrider.car.services.rest.params.ResetValetPinResponse;
import es.neodoo.knightrider.car.services.rest.params.ResponseParamVehicleCommands;
import es.neodoo.knightrider.car.services.rest.params.SetChargeLimitResponse;
import es.neodoo.knightrider.car.services.rest.params.SetChargeLimitToMaxRangeResponse;
import es.neodoo.knightrider.car.services.rest.params.SetChargeLimitToStandardResponse;
import es.neodoo.knightrider.car.services.rest.params.SetTemperatureResponse;
import es.neodoo.knightrider.car.services.rest.params.SetValetModeResponse;
import es.neodoo.knightrider.car.services.rest.params.StartChargingResponse;
import es.neodoo.knightrider.car.services.rest.params.StartHvacSystemResponse;
import es.neodoo.knightrider.car.services.rest.params.StopChargingResponse;
import es.neodoo.knightrider.car.services.rest.params.StopHvacSystemResponse;
import es.neodoo.knightrider.car.services.rest.params.UnlockDoorsResponse;
import es.neodoo.knightrider.car.services.rest.params.VehicleStateParamResponse;
import es.neodoo.knightrider.car.services.rest.params.VehicleStateResponse;
import es.neodoo.knightrider.car.services.rest.params.WakeUpCarResponse;

public class VehicleHardwareInvoker {
	
	public WakeUpCarResponse wakeUpCar(int vehicleId){
		
		WakeUpCarResponse wakeUpCarResponse = new WakeUpCarResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		wakeUpCarResponse.setResponse(responseParamVehicleCommands);
		
		return wakeUpCarResponse;
		
	}

	public SetValetModeResponse setValetMode(int vehicleId, Boolean on, int pin){
		
		SetValetModeResponse setValetModeResponse = new SetValetModeResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		setValetModeResponse.setResponse(responseParamVehicleCommands);
		
		return setValetModeResponse;
	
	}
	
	public ResetValetPinResponse resetValetPin(int vehicleId){
		
		ResetValetPinResponse resetValetPinResponse = new ResetValetPinResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		resetValetPinResponse.setResponse(responseParamVehicleCommands);
		
		return resetValetPinResponse;
		
	}

	public OpenChargePortResponse openChargeDoor(int vehicleId) {
		
		OpenChargePortResponse openChargePortResponse = new OpenChargePortResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		openChargePortResponse.setResponse(responseParamVehicleCommands);
		
		return openChargePortResponse;
	
	}
	
	


	public SetChargeLimitToStandardResponse setChargeLimitToStandard(int vehicleId) {
		
		SetChargeLimitToStandardResponse setChargeLimitToStandardResponse = new SetChargeLimitToStandardResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(false, "Already_standard");
		setChargeLimitToStandardResponse.setResponse(responseParamVehicleCommands);
		
		return setChargeLimitToStandardResponse;
	
	}
	
	public SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRange(int vehicleId) {
		
		SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponse = new SetChargeLimitToMaxRangeResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(false, "Already_max_range");
		setChargeLimitToMaxRangeResponse.setResponse(responseParamVehicleCommands);
		
		return setChargeLimitToMaxRangeResponse;
	
	}

	public SetChargeLimitResponse setChargeLimitT(int vehicleId, int percent) {
		
		SetChargeLimitResponse setChargeLimitResponse = new SetChargeLimitResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		setChargeLimitResponse.setResponse(responseParamVehicleCommands);
		
		return setChargeLimitResponse;
	
	}

	public StartChargingResponse startCharging(int vehicleId) {
		
		StartChargingResponse startChargingResponse = new StartChargingResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(false, "Already_started");
		startChargingResponse.setResponse(responseParamVehicleCommands);

		return startChargingResponse;
	
	}

	public StopChargingResponse stopCharging(int vehicleId) {
		
		StopChargingResponse stopChargingResponse = new StopChargingResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(false, "not_charging");
		stopChargingResponse.setResponse(responseParamVehicleCommands);
		
		return stopChargingResponse;
	
	}

	public FlashLightsResponse flashLights(int vehicleId) {

		FlashLightsResponse flashLightsResponse = new FlashLightsResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		flashLightsResponse.setResponse(responseParamVehicleCommands);
		
		return flashLightsResponse;
	
	}

	public HonkHornResponse honkHorn(int vehicleId) {

		HonkHornResponse honkHornResponse = new HonkHornResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		honkHornResponse.setResponse(responseParamVehicleCommands);
		return honkHornResponse;
	}	

	public UnlockDoorsResponse unlockDoors(int vehicleId) {
		
		UnlockDoorsResponse unlockDoorsResponse = new UnlockDoorsResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		unlockDoorsResponse.setResponse(responseParamVehicleCommands);
		
		return unlockDoorsResponse;
	
	}

	public LockDoorsResponse lockDoors(int vehicleId) {
		
		LockDoorsResponse lockDoorsResponse = new LockDoorsResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		lockDoorsResponse.setResponse(responseParamVehicleCommands);
	
		return lockDoorsResponse;
	
	}

	public SetTemperatureResponse setTemperature(int vehicleId, double driverTemp, double passengerTemp) {
		
		SetTemperatureResponse setTemperatureResponse = new SetTemperatureResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		setTemperatureResponse.setResponse(responseParamVehicleCommands);
	
		return setTemperatureResponse;
	
	}

	public StartHvacSystemResponse startHvacSystem(int vehicleId) {
		
		StartHvacSystemResponse startHvacSystemResponse = new StartHvacSystemResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		startHvacSystemResponse.setResponse(responseParamVehicleCommands);
		
		return startHvacSystemResponse;
	
	}

	public StopHvacSystemResponse stopHvacSystem(int vehicleId) {
		
		StopHvacSystemResponse stopHvacSystemResponse = new StopHvacSystemResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		stopHvacSystemResponse.setResponse(responseParamVehicleCommands);
		
		return stopHvacSystemResponse;
	
	}

	public MovePanoRoofResponse movePanoRoof(int vehicleId, String state, int percent) {
		
		MovePanoRoofResponse movePanoRoofResponse = new MovePanoRoofResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		movePanoRoofResponse.setResponse(responseParamVehicleCommands);
		
		return movePanoRoofResponse;
	
	}

	public RemoteStartResponse remoteStart(int vehicleId) {
		
		RemoteStartResponse remoteStartResponse = new RemoteStartResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		remoteStartResponse.setResponse(responseParamVehicleCommands);
		
		return remoteStartResponse;
	
	}

	public OpenTrunkResponse openTrunk(int vehicleId, String which) {
		
		OpenTrunkResponse openTrunkResponse = new OpenTrunkResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands= new ResponseParamVehicleCommands(true, "");
		openTrunkResponse.setResponse(responseParamVehicleCommands);
		
		return openTrunkResponse;
		
	}

	public MobileAccesResponse mobileAcces(int vehicleId) {
	
		MobileAccesResponse mobileAccesResponse = new MobileAccesResponse();
		mobileAccesResponse.setResponse(true);
		return mobileAccesResponse;
	}

	public ChargeStateResponse chargeState(int vehicleId) {
		
		ChargeStateResponse chargeStateResponse = new ChargeStateResponse();
		ChargeStateParamResponse chargeStateParamResponse= new ChargeStateParamResponse("complete", false, 123, true, 123.79, 321.321, 5.5, 91, -0.5, 
				null, null, 0, 50, 0, 0, null, -1.0, true);
		chargeStateResponse.setResponse(chargeStateParamResponse);
		
		return chargeStateResponse;
	
	}

	public ClimateSettingsResponse climateState(int vehicleId) {
		
		ClimateSettingsResponse climateSettingsResponse = new ClimateSettingsResponse();
		ClimateSettingsParamResponse climateSettingsParamResponse= new ClimateSettingsParamResponse(17.0, 9.5, 22.6, 22.6, false, null, false, 0);
		climateSettingsResponse.setResponse(climateSettingsParamResponse);
		
		return climateSettingsResponse;
	
	}

	public DrivingAndPositionResponse drivingAndPositionState(int vehicleId) {
		
		DrivingAndPositionResponse drivingAndPositionResponse = new DrivingAndPositionResponse();
		DrivingAndPositionParamResponse drivingAndPositionParamResponse= new DrivingAndPositionParamResponse(null, null, 33.4564564567, -85.565467546, 4, 1359863204);
		drivingAndPositionResponse.setResponse(drivingAndPositionParamResponse);
		
		return drivingAndPositionResponse;
	}

	public GuiSettingsResponse guiSetting(int vehicleId) {
		
		GuiSettingsResponse guiSettingsResponse = new GuiSettingsResponse();
		GuiSettingsParamResponse guiSettingsParamResponse= new GuiSettingsParamResponse("mi/hr", "F", "mi/hr", false, "Rated");
		guiSettingsResponse.setResponse(guiSettingsParamResponse);
		
		return guiSettingsResponse;
		
	}

	public VehicleStateResponse vehicleState(int vehicleId) {
		
		VehicleStateResponse vehicleStateResponse = new VehicleStateResponse();
		VehicleStateParamResponse vehicleStateParamResponse= new VehicleStateParamResponse(false, false, false, false, false, false, "1.19.43", true, false,
				"unknown", 0, false, "Base19", false, "Colored", "Base");
		vehicleStateResponse.setResponse(vehicleStateParamResponse);
		
		return null;
	
	}

	
	
	
	
}
