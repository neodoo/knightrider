package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyProfileResponse {

	private MyProfileParamResponse response;

	public void setResponse(MyProfileParamResponse response) {
		this.response = response;
	}

	public MyProfileParamResponse getResponse() {
		return response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static MyProfileResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		MyProfileResponse showMyProfileResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		showMyProfileResponse = mapper.readValue(jsonInString, MyProfileResponse.class);

		return showMyProfileResponse;

	}

	public MyProfileResponse(MyProfileParamResponse response) {
		this.response = response;
	}

	public MyProfileResponse() {}

	public MyProfileResponse buildMyProfileResponse (long numTravels, double time, double cost, double average, String name){

		MyProfileResponse myProfileResponse = new MyProfileResponse();
		MyProfileParamResponse myProfileParamResponse = new MyProfileParamResponse(numTravels, time, cost, average, name);
		myProfileResponse.setResponse(myProfileParamResponse);

		return myProfileResponse;

	}

}
