package es.neodoo.knightrider.car.services.rest.api;

import javax.ws.rs.core.Response;

import es.neodoo.knightrider.car.services.exceptions.TeslaInvokerException;

public interface VehiclesCommandRest {

	Response WakeUpCar(int vehicleId) throws TeslaInvokerException;

	Response SetValetMode(int vehicleId, Boolean on, int pin) throws TeslaInvokerException;

	Response ResetValetPin(int vehicleId) throws TeslaInvokerException;

	Response OpenChargePort(int vehicleId) throws TeslaInvokerException;

	Response setChargeLimitToStandard(int vehicleId) throws TeslaInvokerException;

	Response setChargeLimitToMaxRange(int vehicleId) throws TeslaInvokerException;

	Response setChargeLimit(int vehicleId, int percent) throws TeslaInvokerException;

	Response startCharging(int vehicleId) throws TeslaInvokerException;

	Response stopCharging(int vehicleId) throws TeslaInvokerException;

	Response flashLights(int vehicleId) throws TeslaInvokerException;

	Response honkHorn(int vehicleId) throws TeslaInvokerException;

	Response unlockDoors(int vehicleId) throws TeslaInvokerException;

	Response lockDoors(int vehicleId) throws TeslaInvokerException;

	Response setTemperature(int vehicleId, double driverTemp, double passengerTemp) throws TeslaInvokerException;

	Response startHvacSystem(int vehicleId) throws TeslaInvokerException;

	Response stopHvacSystem(int vehicleId) throws TeslaInvokerException;

	Response MovePanoRoof(int vehicleId, String state, int percent) throws TeslaInvokerException;

	Response remoteStart(int vehicleId, String password) throws TeslaInvokerException;

	Response openTrunk(int vehicleId, String which) throws TeslaInvokerException;

}
