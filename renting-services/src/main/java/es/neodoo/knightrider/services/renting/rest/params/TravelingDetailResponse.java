package es.neodoo.knightrider.services.renting.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public class TravelingDetailResponse {

	private TravelingDetailParamResponse response;
	
	public void setResponse(TravelingDetailParamResponse response) {
		this.response = response;
	}

	public TravelingDetailParamResponse getResponse() {
		return response;
	}
	
	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}
	
	
	public static TravelingDetailResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		TravelingDetailResponse showRentDetailsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		showRentDetailsResponse = mapper.readValue(jsonInString, TravelingDetailResponse.class);

		return showRentDetailsResponse;
		
	}

	public TravelingDetailResponse(TravelingDetailParamResponse response) {
		this.response = response;
	}

	public TravelingDetailResponse() {}
	
	public TravelingDetailResponse buildTravelingDetailResponse (VehicleTraveling vehicleTraveling){
		
		TravelingDetailResponse travelingDetailResponse = new TravelingDetailResponse();
		TravelingDetailParamResponse travelingDetailParamResponse = new TravelingDetailParamResponse(vehicleTraveling);
		travelingDetailResponse.setResponse(travelingDetailParamResponse);
		
		return travelingDetailResponse;
		
	}

}

