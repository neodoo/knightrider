package es.neodoo.knightrider.car.services.simulation.hardware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.car.services.exceptions.VehicleHardwareInvokerException;

public class VehicleHardwareInvoker {

	private static final Log log = LogFactory.getLog(VehicleHardwareInvoker.class);


	public VehicleHardwareInvoker() {
		super();
	}

	public boolean wakeUpCar(int vehicleId) throws VehicleHardwareInvokerException {

		boolean wakeUpCar = false;

		try {
			wakeUpCar = true;
		} catch (Exception e) {
			log.error("error waking up vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error waking up vehicle");
		}

		return wakeUpCar;

	}

	public boolean setValetMode(int vehicleId, Boolean on, int pin) throws VehicleHardwareInvokerException {

		boolean setValetMode = false;

		try {
			setValetMode = true;
		} catch (Exception e) {
			log.error("error setting valet mode vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error setting valet mode of the vehicle");
		}

		return setValetMode;


	}

	public boolean resetValetPin(int vehicleId) throws VehicleHardwareInvokerException {

		boolean resetValetMode = false;

		try {
			resetValetMode = true;
		} catch (Exception e) {
			log.error("error reseting valet mode vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error reseting valet mode of the vehicle");
		}

		return resetValetMode;


	}

	public boolean openChargeDoor(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean openChargeDoor = false;

		try {
			openChargeDoor = true;
		} catch (Exception e) {
			log.error("error opening cahrge door vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error opening charge door of the vehicle");
		}

		return openChargeDoor;

	}




	public boolean setChargeLimitToStandard(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean setChargeLimitToStandard = false;

		try {
			setChargeLimitToStandard = true;
		} catch (Exception e) {
			log.error("error setting charge limit to standard of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error setting cahrge limit to standard of the vehicle");
		}

		return setChargeLimitToStandard;

	}

	public boolean setChargeLimitToMaxRange(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean setChargeLimitToMaxRange = false;

		try {
			setChargeLimitToMaxRange = true;
		} catch (Exception e) {
			log.error("error setting charge limit to max range of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error setting limit to max range of the of the vehicle");
		}

		return setChargeLimitToMaxRange;

	}

	public boolean setChargeLimit(int vehicleId, int percent) throws VehicleHardwareInvokerException  {

		boolean setChargeLimit = false;

		try {
			setChargeLimit = true;
		} catch (Exception e) {
			log.error("error setting charge limit of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error setting charge limit of the vehicle");
		}

		return setChargeLimit;

	}

	public boolean startCharging(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean startCharging = false;

		try {
			startCharging = true;
		} catch (Exception e) {
			log.error("error start charging vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error start charging of the vehicle");
		}

		return startCharging;

	}

	public boolean stopCharging(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean stopCharging = false;

		try {
			stopCharging = true;
		} catch (Exception e) {
			log.error("error stop charging  vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error stop charging the vehicle");
		}

		return stopCharging;

	}

	public boolean flashLights(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean flashLights = false;

		try {
			flashLights = true;
		} catch (Exception e) {
			log.error("error flash lights vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error flash lights of the vehicle");
		}

		return flashLights;
	}

	public boolean honkHorn(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean honkHorn = false;

		try {
			honkHorn = true;
		} catch (Exception e) {
			log.error("error honk horn vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error honk horn of the vehicle");
		}

		return honkHorn;
	}	

	public boolean unlockDoors(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean unlockDoors = false;

		try {
			unlockDoors = true;
		} catch (Exception e) {
			log.error("error unlocking doors of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error unlocking doors of the vehicle");
		}

		return unlockDoors;

	}

	public boolean lockDoors(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean lockDoors = false;

		try {
			lockDoors = true;
		} catch (Exception e) {
			log.error("error locking doors of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error locking doors of the vehicle");
		}

		return lockDoors;

	}

	public boolean setTemperature(int vehicleId, double driverTemp, double passengerTemp) throws VehicleHardwareInvokerException {

		boolean setTemperature = false;

		try {
			setTemperature = true;
		} catch (Exception e) {
			log.error("error setting temperature of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error setting temperature of the vehicle");
		}

		return setTemperature;
	}

	public boolean startHvacSystem(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean startHvacSystem = false;

		try {
			startHvacSystem = true;
		} catch (Exception e) {
			log.error("error starting HVAC system of the vehicle vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error starting HVAC system of the vehicle");
		}

		return startHvacSystem;

	}

	public boolean stopHvacSystem(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean stopHvacSystem = false;

		try {
			stopHvacSystem = true;
		} catch (Exception e) {
			log.error("error stopping HVAC system of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error stopping HVAC system of the vehicle");
		}

		return stopHvacSystem;

	}

	public boolean movePanoRoof(int vehicleId, String state, int percent) throws VehicleHardwareInvokerException  {

		boolean movePanoRoof = false;

		try {
			movePanoRoof = true;
		} catch (Exception e) {
			log.error("error moving pano roof of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error moving pano roof of the vehicle");
		}

		return movePanoRoof;

	}

	public boolean remoteStart(int vehicleId) throws VehicleHardwareInvokerException  {

		boolean remoteStart = false;

		try {
			remoteStart = true;
		} catch (Exception e) {
			log.error("error remote starting of the vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error remote starting of the vehicle");
		}

		return remoteStart;

	}

	public boolean openTrunk(int vehicleId, String which) throws VehicleHardwareInvokerException {

		boolean openTrunk = false;

		try {
			openTrunk = true;
		} catch (Exception e) {
			log.error("error opning trunk of vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error opening trunk of the vehicle");
		}

		return openTrunk;

	}

	public boolean mobileAcces(int vehicleId) throws VehicleHardwareInvokerException {

		boolean mobileAcces = false;

		try {
			mobileAcces = true;
		} catch (Exception e) {
			log.error("error accesing with mobile at vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error accesing mobile at the vehicle");
		}

		return mobileAcces;
	}

	public String chargeState(int vehicleId) throws VehicleHardwareInvokerException {

		String chargeState = null;

		try {
			chargeState = "Complete";
		} catch (Exception e) {
			log.error("error with charge state with vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error with charge state with the vehicle");
		}

		return chargeState;

	}

	public double climateSettings(int vehicleId) throws VehicleHardwareInvokerException {

		Double climateSettings = null;

		try {
			climateSettings = 17.0;
		} catch (Exception e) {
			log.error("error with climate settings with vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error with climnate settings with the vehicle");
		}

		return climateSettings;

	}

	public String drivingAndPositionState(int vehicleId) throws VehicleHardwareInvokerException {

		String drivingAndPositionState = null;

		try {
			drivingAndPositionState = null;
		} catch (Exception e) {
			log.error("error with climate settings with vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error with climnate settings with the vehicle");
		}

		return drivingAndPositionState;

	}

	public String guiSettings(int vehicleId) throws VehicleHardwareInvokerException {

		String guiSettings = null;

		try {
			guiSettings = "mi/hr";
		} catch (Exception e) {
			log.error("error with gui settings with vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error with gui settings with the vehicle");
		}

		return guiSettings;

	}

	public boolean vehicleState(int vehicleId) throws VehicleHardwareInvokerException {

		boolean vehicleState = false;

		try {
			vehicleState = false;
		} catch (Exception e) {
			log.error("error with vehicle state with vehicle:" + vehicleId);
			throw new VehicleHardwareInvokerException ("error with vehcile state with the vehicle");
		}

		return vehicleState;


	}





}
