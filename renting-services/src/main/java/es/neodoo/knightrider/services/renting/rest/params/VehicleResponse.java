package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.neodoo.knightrider.services.renting.model.vo.Vehicle;


public class VehicleResponse {

	private  List<VehicleParamResponse> response = new ArrayList<VehicleParamResponse>();

	private int count;

	public VehicleResponse() {}

	public VehicleResponse(List<VehicleParamResponse> response, int count) {
		this.response = response;
		this.count = count;
	}

	public List<VehicleParamResponse> getResponse() {
		return response;
	}

	public void setResponse(List<VehicleParamResponse> response) {
		this.response = response;
	}
	public void addResponse(VehicleParamResponse response) {
		this.response.add(response);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public VehicleResponse buildVehicleResponse(List<Vehicle> vehicles){

		VehicleResponse vehicleResponse = new VehicleResponse();

		for (Vehicle v : vehicles){

			VehicleParamResponse vehiclesParamResponse = new VehicleParamResponse(v);
			vehicleResponse.addResponse(vehiclesParamResponse);

		}

		vehicleResponse.setCount(vehicleResponse.getResponse().size());

		return vehicleResponse;

	}
	
	public VehicleResponse buildVehicleResponse(Vehicle vehicle){
		
		VehicleResponse vehicleResponse = new VehicleResponse();
		VehicleParamResponse vehiclesParamResponse = new VehicleParamResponse(vehicle);
		vehicleResponse.addResponse(vehiclesParamResponse);
		vehicleResponse.setCount(vehicleResponse.getResponse().size());
		return vehicleResponse;
		
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public VehicleResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		VehicleResponse lVehiclesResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		lVehiclesResponse = mapper.readValue(jsonInString, VehicleResponse.class);

		return lVehiclesResponse;

	}
}
