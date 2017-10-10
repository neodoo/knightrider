package es.neodoo.knightrider.car.services.rest.api;

import javax.ws.rs.core.Response;

import es.neodoo.knightrider.car.services.exceptions.VehicleHardwareInvokerException;

public interface VehiclesRest {

	Response listVehicles() throws VehicleHardwareInvokerException;

	Response mobileAcces(int vehicleId) throws VehicleHardwareInvokerException;

	Response chargeState(int vehicleId) throws VehicleHardwareInvokerException;

	Response climateSettings(int vehicleId) throws VehicleHardwareInvokerException;

	Response drivingAndPosition(int vehicleId) throws VehicleHardwareInvokerException;

	Response guiSettings(int vehicleId) throws VehicleHardwareInvokerException;

	Response vehicleState(int vehicleId) throws VehicleHardwareInvokerException;

}