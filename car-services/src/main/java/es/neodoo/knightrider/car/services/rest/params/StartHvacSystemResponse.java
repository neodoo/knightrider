package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StartHvacSystemResponse {
	
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

	public static StartHvacSystemResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		StartHvacSystemResponse startHvacSystemResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		startHvacSystemResponse = mapper.readValue(jsonInString, StartHvacSystemResponse.class);

		return startHvacSystemResponse;

	}

	public StartHvacSystemResponse(){}
	
	public StartHvacSystemResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;

	}
	
	public StartHvacSystemResponse buildStartHvacSystemResponse(Boolean result, String reason) {

		StartHvacSystemResponse startHvacSystemResponse = new StartHvacSystemResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		startHvacSystemResponse.setResponse(responseParamVehicleCommands);

		return startHvacSystemResponse;

	}

}