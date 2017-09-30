package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
		El Response de la llamada ListVehicles consta de los siguientes parámetros:
				- Una lista de ListVehiclesParamResponse
				- Entero ( cantidad de objetos ListVehiclesParamResponse en la lista) 
 {
  "response": [
    {
      "color": null,
      "display_name": null,
      "id": 321,
      "option_codes": "MS01,RENA,TM00,DRLH,PF00,BT85,PBCW,RFPO,WT19,IBMB,IDPB,TR00,SU01,SC01,TP01,AU01,CH00,HP00,PA00,PS00,AD02,X020,X025,X001,X003,X007,X011,X013",
      "user_id": 123,
      "vehicle_id": 1234567890,
      "vin": "5YJSA1CN5CFP01657",
      "tokens": [
        "x",
        "x"
      ],
      "state": "online"
    }
  ],
  "count": 1
  }

 */
public class ListVehiclesResponse {

	private  List<ListVehiclesParamResponse> response;

	private int count;

	public ListVehiclesResponse() {}
	
	public ListVehiclesResponse(List<ListVehiclesParamResponse> response, int count) {
		this.response = response;
		this.count = count;
	}

	public List<ListVehiclesParamResponse> getResponse() {
		return response;
	}

	public void setResponse(List<ListVehiclesParamResponse> response) {
		this.response = response;
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

	public ListVehiclesResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		ListVehiclesResponse lVehiclesResponse;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		lVehiclesResponse = mapper.readValue(jsonInString, ListVehiclesResponse.class);

		return lVehiclesResponse;
	
	}

}