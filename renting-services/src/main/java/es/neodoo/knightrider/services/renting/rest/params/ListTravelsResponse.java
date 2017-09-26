package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListTravelsResponse {
	private  List<ListTravelsParamResponse> response = new ArrayList<ListTravelsParamResponse>();

	private int count;

	public ListTravelsResponse() {}
	
	public ListTravelsResponse(List<ListTravelsParamResponse> response, int count) {
		this.response = response;
		this.count = count;
	}

	public List<ListTravelsParamResponse> getResponse() {
		return response;
	}

	public void setResponse(List<ListTravelsParamResponse> response) {
		this.response = response;
	}
	public void addResponse(ListTravelsParamResponse response) {
		this.response.add(response);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public ListTravelsResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ListTravelsResponse lTravelsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		lTravelsResponse = mapper.readValue(jsonInString, ListTravelsResponse.class);

		return lTravelsResponse;
	
	}
}
