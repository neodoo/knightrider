package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenTrunkResponse {
	
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

	public static OpenTrunkResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		OpenTrunkResponse openTrunkResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		openTrunkResponse = mapper.readValue(jsonInString, OpenTrunkResponse.class);

		return openTrunkResponse;

	}

	public OpenTrunkResponse(){}
	
	public OpenTrunkResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}

}