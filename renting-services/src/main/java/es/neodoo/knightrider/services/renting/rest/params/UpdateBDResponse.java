package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UpdateBDResponse {

	private UpdateBDParamResponse response;

	public UpdateBDParamResponse getResponse() {
		return response;
	}

	public void setResponse(UpdateBDParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static UpdateBDResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		UpdateBDResponse updateBDResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		updateBDResponse = mapper.readValue(jsonInString, UpdateBDResponse.class);

		return updateBDResponse;
	
	}

	public UpdateBDResponse(){}

	public UpdateBDResponse(UpdateBDParamResponse response) {
		this.response = response;
	}
}