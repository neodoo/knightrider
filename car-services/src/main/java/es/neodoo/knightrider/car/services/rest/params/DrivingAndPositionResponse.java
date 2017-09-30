package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*	El Response de la llamada DrivingAndPostion consta de los siguientes parámetros
  {
  "response": {
    "shift_state": null,          //
    "speed": null,                //
    "latitude": 33.794839,        // degrees N of equator
    "longitude": -84.401593,      // degrees W of the prime meridian
    "heading": 4,                 // integer compass heading, 0-359
    "gps_as_of": 1359863204       // Unix timestamp of GPS fix
  }
}
 */

public class DrivingAndPositionResponse {

	private DrivingAndPositionParamResponse response;

	public DrivingAndPositionParamResponse getResponse() {
		return response;
	}

	public void setResponse(DrivingAndPositionParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static DrivingAndPositionResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		DrivingAndPositionResponse drivingAndPositionResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		drivingAndPositionResponse = mapper.readValue(jsonInString, DrivingAndPositionResponse.class);

		return drivingAndPositionResponse;
	
	}

	public DrivingAndPositionResponse(){}
	
	public DrivingAndPositionResponse(DrivingAndPositionParamResponse drivingAndPositionParamResponse){
	
		response=drivingAndPositionParamResponse;
	
	}

}