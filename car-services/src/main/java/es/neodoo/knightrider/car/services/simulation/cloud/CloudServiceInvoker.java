package es.neodoo.knightrider.car.services.simulation.cloud;

import java.util.ArrayList;
import java.util.List;

import es.neodoo.knightrider.car.services.rest.params.ListVehiclesParamResponse;
import es.neodoo.knightrider.car.services.rest.params.ListVehiclesResponse;

public class CloudServiceInvoker {

	public ListVehiclesResponse listVehicles() {

		List<ListVehiclesParamResponse> listVehiclesResponseParam = new ArrayList<ListVehiclesParamResponse>();
		String[] tabla = {"token 1","token2"};
		ListVehiclesParamResponse vehiclesResponseParam= new ListVehiclesParamResponse("amarillo","nombreAleatorio",123,"opciones de algo",123,321,"cadena para el vin",tabla,"online");
		listVehiclesResponseParam.add(vehiclesResponseParam);
		int count = 1;
		ListVehiclesResponse vehiclesResponse = new ListVehiclesResponse (listVehiclesResponseParam,count);
		
		return vehiclesResponse;
	
	}

}
