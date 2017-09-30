package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*	El Response de la llamada SetChargeLimitToMaxRange consta de los siguientes parámetros
  {
  "response": {
    "result": false,
    "reason": "already_max_range"
  }
}
 */

public class SetChargeLimitToMaxRangeResponse {

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

	public static SetChargeLimitToMaxRangeResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		SetChargeLimitToMaxRangeResponse setChargeLimitToMaxRangeResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		setChargeLimitToMaxRangeResponse = mapper.readValue(jsonInString, SetChargeLimitToMaxRangeResponse.class);

		return setChargeLimitToMaxRangeResponse;

	}

	public SetChargeLimitToMaxRangeResponse(){}
	
	public SetChargeLimitToMaxRangeResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}

}