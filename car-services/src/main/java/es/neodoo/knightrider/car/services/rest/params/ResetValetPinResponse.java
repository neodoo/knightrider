package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResetValetPinResponse {

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

	public static ResetValetPinResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ResetValetPinResponse resetValetPinResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		resetValetPinResponse = mapper.readValue(jsonInString, ResetValetPinResponse.class);

		return resetValetPinResponse;
	
	}

	public ResetValetPinResponse(){}
	
	public ResetValetPinResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}
	
	public ResetValetPinResponse buildResetValetPinResponse(Boolean result, String reason) {

		ResetValetPinResponse resetValetPinResponse = new ResetValetPinResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		resetValetPinResponse.setResponse(responseParamVehicleCommands);

		return resetValetPinResponse;

	}

}