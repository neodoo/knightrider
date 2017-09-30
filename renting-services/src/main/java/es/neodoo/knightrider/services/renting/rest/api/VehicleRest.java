package es.neodoo.knightrider.services.renting.rest.api;

import javax.ws.rs.core.Response;

public interface VehicleRest {

	Response test();

	Response getVehiclesUnblocked();

	Response blocked(String username);

	Response travelingVehicles(String username);

	Response block(int vehicleId, String username);

	Response unblock(int vehicleId, String username);

	Response travelStart(int vehicleId, String username);

	Response travelFinish(int vehicleId, String username);

}