package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*		El Response de la llamada SetChargeLimit consta de los siguientes parámetros
{
  "response": {
    "result": true,
    "reason": ""
  }
}
 */

public class SetChargeLimitResponse {
	
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

	public static SetChargeLimitResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		SetChargeLimitResponse setChargeLimitResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		setChargeLimitResponse = mapper.readValue(jsonInString, SetChargeLimitResponse.class);

		return setChargeLimitResponse;
	
	}

	public SetChargeLimitResponse(){}
	
	public SetChargeLimitResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}

}