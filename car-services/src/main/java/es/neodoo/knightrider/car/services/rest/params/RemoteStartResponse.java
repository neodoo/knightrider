package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoteStartResponse {

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

	public static RemoteStartResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		RemoteStartResponse remoteStartResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		remoteStartResponse = mapper.readValue(jsonInString, RemoteStartResponse.class);

		return remoteStartResponse;
	
	}

	public RemoteStartResponse(){}
	
	public RemoteStartResponse(ResponseParamVehicleCommands responseParamVehicleCommands){
	
		response=responseParamVehicleCommands;
	
	}
	
	public RemoteStartResponse buildRemoteStartResponse(Boolean result, String reason) {

		RemoteStartResponse remoteStartResponse = new RemoteStartResponse();
		ResponseParamVehicleCommands responseParamVehicleCommands = new ResponseParamVehicleCommands();
		responseParamVehicleCommands.setResult(result);
		responseParamVehicleCommands.setReason(reason);
		remoteStartResponse.setResponse(responseParamVehicleCommands);

		return remoteStartResponse;

	}

}