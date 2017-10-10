package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * {	El Response de la llamada guiSettings consta de los siguientes parámetros
  "response": {
    "gui_distance_units": "mi/hr",
    "gui_temperature_units": "F",
    "gui_charge_rate_units": "mi/hr",
    "gui_24_hour_time": false,
    "gui_range_display": "Rated"
  }
}
 */

public class GuiSettingsResponse {
	
	private GuiSettingsParamResponse response;

	public GuiSettingsParamResponse getResponse() {
		return response;
	}

	public void setResponse(GuiSettingsParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static GuiSettingsResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		GuiSettingsResponse guiSettingsResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		guiSettingsResponse = mapper.readValue(jsonInString, GuiSettingsResponse.class);

		return guiSettingsResponse;
	
	}

	public GuiSettingsResponse(){}
	
	public GuiSettingsResponse(GuiSettingsParamResponse guiSettingsParamResponse){
	
		response=guiSettingsParamResponse;
	
	}
	
	public GuiSettingsResponse buildGuiSettingsResponse(String guiSettings) {

		GuiSettingsResponse guiSettingsResponse = new GuiSettingsResponse();
		GuiSettingsParamResponse guiSettingsParamResponse= new GuiSettingsParamResponse(guiSettings, "F", "mi/hr", false, "Rated");
		guiSettingsResponse.setResponse(guiSettingsParamResponse);

		return guiSettingsResponse;

	}
	
	

}