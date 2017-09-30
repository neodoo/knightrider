package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * El Response de la llamada VehicleState consta de los siguientes parámetros
{
  "response": {
    "df": false,                  // driver's side front door open
    "dr": false,                  // driver's side rear door open
    "pf": false,                  // passenger's side front door open
    "pr": false,                  // passenger's side rear door open
    "ft": false,                  // front trunk is open
    "rt": false,                  // rear trunk is open
    "car_verson": "1.19.42",      // car firmware version
    "locked": true,               // car is locked
    "sun_roof_installed": false,  // panoramic roof is installed
    "sun_roof_state": "unknown",
    "sun_roof_percent_open": 0,   // null if not installed
    "dark_rims": false,           // gray rims installed
    "wheel_type": "Base19",       // wheel type installed
    "has_spoiler": false,         // spoiler is installed
    "roof_color": "Colored",      // "None" for panoramic roof
    "perf_config": "Base"
  }
}
 */

public class VehicleStateResponse {

	private VehicleStateParamResponse response;

	public VehicleStateParamResponse getResponse() {
		return response;
	}

	public void setResponse(VehicleStateParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static VehicleStateResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		VehicleStateResponse vehicleStateResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		vehicleStateResponse = mapper.readValue(jsonInString, VehicleStateResponse.class);

		return vehicleStateResponse;

	}

	public VehicleStateResponse(){}

	public VehicleStateResponse(VehicleStateParamResponse vehicleStateParamResponse){
	
		response=vehicleStateParamResponse;
	
	}

}