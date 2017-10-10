package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*   El Response de la llamada ChargeState consta de los siguientes parámetros
{	
	  "response": {
	    "charging_state": "Complete",  // "Charging", ??
	    "charge_to_max_range": false,  // current std/max-range setting
	    "max_range_charge_counter": 0,
	    "fast_charger_present": false, // connected to Supercharger?
	    "battery_range": 239.02,       // rated miles
	    "est_battery_range": 155.79,   // range estimated from recent driving
	    "ideal_battery_range": 275.09, // ideal miles
	    "battery_level": 91,           // integer charge percentage
	    "battery_current": -0.6,       // current flowing into battery
	    "charge_starting_range": null,
	    "charge_starting_soc": null,
	    "charger_voltage": 0,          // only has value while charging
	    "charger_pilot_current": 40,   // max current allowed by charger & adapter
	    "charger_actual_current": 0,   // current actually being drawn
	    "charger_power": 0,            // kW (rounded down) of charger
	    "time_to_full_charge": null,   // valid only while charging
	    "charge_rate": -1.0,           // float mi/hr charging or -1 if not charging
	    "charge_port_door_open": true
	  }
	}
 */

public class ChargeStateResponse {

	private ChargeStateParamResponse response;

	public ChargeStateParamResponse getResponse() {
		return response;
	}

	public void setResponse(ChargeStateParamResponse response) {
		this.response = response;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static ChargeStateResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ChargeStateResponse cStateReponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		cStateReponse = mapper.readValue(jsonInString, ChargeStateResponse.class);

		return cStateReponse;
	
	}

	public ChargeStateResponse(){}
	
	public ChargeStateResponse buildChargeStateResponse(String chargingState) {

		ChargeStateResponse chargeStateResponse = new ChargeStateResponse();
		ChargeStateParamResponse chargeStateParamResponse = new ChargeStateParamResponse(chargingState, false, 123, true, 123.79, 321.321, 5.5, 91, -0.5, 
				null, null, 0, 50, 0, 0, null, -1.0, true);
		chargeStateResponse.setResponse(chargeStateParamResponse);

		return chargeStateResponse;

	}

}