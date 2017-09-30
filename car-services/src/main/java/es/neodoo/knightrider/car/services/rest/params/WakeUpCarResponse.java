package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*		El Response de la llamada WakeUpCar consta de los siguientes parámetros
 * {
  "response": {
    "result": true,
    "reason": ""
  }
}
 */

public class WakeUpCarResponse {
	
	private ResponseParamVehicleCommands response;

	public ResponseParamVehicleCommands getResponse() {
		return response;
	}

	public void setResponse(ResponseParamVehicleCommands response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static WakeUpCarResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		WakeUpCarResponse wakeUpCarResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		wakeUpCarResponse = mapper.readValue(jsonInString, WakeUpCarResponse.class);

		return wakeUpCarResponse;

	}

	public WakeUpCarResponse(){}
	
	public WakeUpCarResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}

}