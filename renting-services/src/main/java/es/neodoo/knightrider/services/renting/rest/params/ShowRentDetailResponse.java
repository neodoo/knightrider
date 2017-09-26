package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ShowRentDetailResponse {

	private ShowRentDetailParamResponse response;
	
	public void setResponse(ShowRentDetailParamResponse response) {
		this.response = response;
	}

	public ShowRentDetailParamResponse getResponse() {
		return response;
	}
	
	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}
	
	
	public static ShowRentDetailResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ShowRentDetailResponse showRentDetailsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		showRentDetailsResponse = mapper.readValue(jsonInString, ShowRentDetailResponse.class);

		return showRentDetailsResponse;
		
	}

	public ShowRentDetailResponse(ShowRentDetailParamResponse response) {
		this.response = response;
	}

	public ShowRentDetailResponse() {}

}

