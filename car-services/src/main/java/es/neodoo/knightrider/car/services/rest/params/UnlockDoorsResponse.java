package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnlockDoorsResponse {
	
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

	public static UnlockDoorsResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		UnlockDoorsResponse unlockDoorsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		unlockDoorsResponse = mapper.readValue(jsonInString, UnlockDoorsResponse.class);

		return unlockDoorsResponse;

	}

	public UnlockDoorsResponse(){}
	
	public UnlockDoorsResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}
	
	public UnlockDoorsResponse buildUnlockDoorsResponse(Boolean result, String reason) {

		UnlockDoorsResponse unlockDoorsResponse = new UnlockDoorsResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		unlockDoorsResponse.setResponse(responseParamVehicleCommands);

		return unlockDoorsResponse;

	}

}