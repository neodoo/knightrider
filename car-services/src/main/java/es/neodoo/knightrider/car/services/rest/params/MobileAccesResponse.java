package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*	El Response de la llamada MobileAcces consta del siguiente parámetro
  {	
  "response": true		}
 */

public class MobileAccesResponse {

	private boolean response;

	public MobileAccesResponse(){}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static MobileAccesResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		MobileAccesResponse mAResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		mAResponse = mapper.readValue(jsonInString, MobileAccesResponse.class);

		return mAResponse;
	
	}

}