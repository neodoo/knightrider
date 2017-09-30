package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * {	El Response de la llamada ClimateSettings consta de los siguientes parámetros
  "response": {
    "inside_temp": 17.0,          // degC inside car
    "outside_temp": 9.5,          // degC outside car or null
    "driver_temp_setting": 22.6,  // degC of driver temperature setpoint
    "passenger_temp_setting": 22.6, // degC of passenger temperature setpoint
    "is_auto_conditioning_on": false, // apparently even if on
    "is_front_defroster_on": null, // null or boolean as integer?
    "is_rear_defroster_on": false,
    "fan_status": 0               // fan speed 0-6 or null
  }
}
 */
public class ClimateSettingsResponse {

	private ClimateSettingsParamResponse response;

	public ClimateSettingsParamResponse getResponse() {
		return response;
	}

	public void setResponse(ClimateSettingsParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static ClimateSettingsResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ClimateSettingsResponse climateSettingsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		climateSettingsResponse = mapper.readValue(jsonInString, ClimateSettingsResponse.class);

		return climateSettingsResponse;
		
	}

	public ClimateSettingsResponse(){}
	
	public ClimateSettingsResponse(ClimateSettingsParamResponse climateSettingsParamResponse){
		
		response=climateSettingsParamResponse;
	
	}

}
