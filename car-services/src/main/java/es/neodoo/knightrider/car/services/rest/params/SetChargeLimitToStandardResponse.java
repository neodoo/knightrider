package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*		El Response de la llamada SetChargeLimitToStandard consta de los siguientes parámetros
 * {
  "response": {
    "result": false,
    "reason": "already_standard"
  }
}
 */

public class SetChargeLimitToStandardResponse {
	
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

	public static SetChargeLimitToStandardResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		SetChargeLimitToStandardResponse setChargeLimitToStandardResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		setChargeLimitToStandardResponse = mapper.readValue(jsonInString, SetChargeLimitToStandardResponse.class);

		return setChargeLimitToStandardResponse;

	}

	public SetChargeLimitToStandardResponse(){}
	
	public SetChargeLimitToStandardResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}
	
	public SetChargeLimitToStandardResponse buildSetChargeLimitToStandardResponse(Boolean result, String reason) {

		SetChargeLimitToStandardResponse setChargeLimitToStandardResponse = new SetChargeLimitToStandardResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		setChargeLimitToStandardResponse.setResponse(responseParamVehicleCommands);

		return setChargeLimitToStandardResponse;

	}

}