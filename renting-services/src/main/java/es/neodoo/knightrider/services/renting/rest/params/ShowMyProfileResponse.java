package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShowMyProfileResponse {

	private ShowMyProfileParamResponse response;
	
	public void setResponse(ShowMyProfileParamResponse response) {
		this.response = response;
	}

	public ShowMyProfileParamResponse getResponse() {
		return response;
	}
	
	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}
	
	public static ShowMyProfileResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ShowMyProfileResponse showMyProfileResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		showMyProfileResponse = mapper.readValue(jsonInString, ShowMyProfileResponse.class);

		return showMyProfileResponse;
		
	}

	public ShowMyProfileResponse(ShowMyProfileParamResponse response) {
		this.response = response;
	}

	public ShowMyProfileResponse() {}

}
