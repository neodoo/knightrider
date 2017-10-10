package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovePanoRoofResponse {
	
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

	public static MovePanoRoofResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		MovePanoRoofResponse movePanoRoofResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		movePanoRoofResponse = mapper.readValue(jsonInString, MovePanoRoofResponse.class);

		return movePanoRoofResponse;

	}

	public MovePanoRoofResponse(){}
	
	public MovePanoRoofResponse(ResponseParamVehicleCommands responseParamVehicleCommands){

		response=responseParamVehicleCommands;
	
	}
	
	public MovePanoRoofResponse buildMovePanoRoofResponse(Boolean result, String reason) {

		MovePanoRoofResponse movePanoRoofResponse = new MovePanoRoofResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		movePanoRoofResponse.setResponse(responseParamVehicleCommands);

		return movePanoRoofResponse;

	}

}