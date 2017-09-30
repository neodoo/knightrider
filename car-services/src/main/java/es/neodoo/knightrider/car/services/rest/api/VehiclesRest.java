package es.neodoo.knightrider.car.services.rest.api;

import javax.ws.rs.core.Response;

import es.neodoo.knightrider.car.services.exceptions.TeslaInvokerException;

public interface VehiclesRest {

	Response listVehicles() throws TeslaInvokerException;

	Response mobileAcces(int vehicleId) throws TeslaInvokerException;

	Response chargeState(int vehicleId) throws TeslaInvokerException;

	Response climateSettings(int vehicleId) throws TeslaInvokerException;

	Response drivingAndPosition(int vehicleId) throws TeslaInvokerException;

	Response guiSettings(int vehicleId) throws TeslaInvokerException;

	Response vehicleState(int vehicleId) throws TeslaInvokerException;

}