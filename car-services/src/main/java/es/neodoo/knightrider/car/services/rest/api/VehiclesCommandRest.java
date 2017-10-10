package es.neodoo.knightrider.car.services.rest.api;

import javax.ws.rs.core.Response;

import es.neodoo.knightrider.car.services.exceptions.VehicleHardwareInvokerException;

public interface VehiclesCommandRest {

	Response wakeUpCar(int vehicleId) throws VehicleHardwareInvokerException;

	Response setValetMode(int vehicleId, Boolean on, int pin) throws VehicleHardwareInvokerException;

	Response resetValetPin(int vehicleId) throws VehicleHardwareInvokerException;

	Response openChargePort(int vehicleId) throws VehicleHardwareInvokerException;

	Response setChargeLimitToStandard(int vehicleId) throws VehicleHardwareInvokerException;

	Response setChargeLimitToMaxRange(int vehicleId) throws VehicleHardwareInvokerException;

	Response setChargeLimit(int vehicleId, int percent) throws VehicleHardwareInvokerException;

	Response startCharging(int vehicleId) throws VehicleHardwareInvokerException;

	Response stopCharging(int vehicleId) throws VehicleHardwareInvokerException;

	Response flashLights(int vehicleId) throws VehicleHardwareInvokerException;

	Response honkHorn(int vehicleId) throws VehicleHardwareInvokerException;

	Response unlockDoors(int vehicleId) throws VehicleHardwareInvokerException;

	Response lockDoors(int vehicleId) throws VehicleHardwareInvokerException;

	Response setTemperature(int vehicleId, double driverTemp, double passengerTemp) throws VehicleHardwareInvokerException;

	Response startHvacSystem(int vehicleId) throws VehicleHardwareInvokerException;

	Response stopHvacSystem(int vehicleId) throws VehicleHardwareInvokerException;

	Response MovePanoRoof(int vehicleId, String state, int percent) throws VehicleHardwareInvokerException;

	Response remoteStart(int vehicleId, String password) throws VehicleHardwareInvokerException;

	Response openTrunk(int vehicleId, String which) throws VehicleHardwareInvokerException;

}
