package es.neodoo.knightrider.car.services.rest.params;

/*		El Response de la llamada SetValetMode consta de los siguientes parámetros
 * {
  "response": {
    "result": true,
    "reason": ""
  }
}
 */

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SetValetModeResponse {
	
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

	public static SetValetModeResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		SetValetModeResponse setValetResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		setValetResponse = mapper.readValue(jsonInString, SetValetModeResponse.class);

		return setValetResponse;
	
	}

	public SetValetModeResponse(){}
	
	public SetValetModeResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}

}