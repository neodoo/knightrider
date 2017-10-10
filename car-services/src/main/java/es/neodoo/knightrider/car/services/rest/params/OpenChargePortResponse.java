package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*	El Response de la llamada OpenChargePort consta de los siguientes parámetros
 * {
  "response": {
    "result": true,
    "reason": ""
  }
}
 */

public class OpenChargePortResponse {

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

	public static OpenChargePortResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		OpenChargePortResponse openChargePortResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		openChargePortResponse = mapper.readValue(jsonInString, OpenChargePortResponse.class);

		return openChargePortResponse;

	}

	public OpenChargePortResponse(){}
	
	public OpenChargePortResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}
	
	public OpenChargePortResponse buildOpenChargePortResponse(Boolean result, String reason) {

		OpenChargePortResponse openChargePortResponse = new OpenChargePortResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		openChargePortResponse.setResponse(responseParamVehicleCommands);

		return openChargePortResponse;

	}

}