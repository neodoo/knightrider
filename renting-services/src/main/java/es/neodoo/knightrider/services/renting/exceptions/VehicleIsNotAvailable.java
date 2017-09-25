package es.neodoo.knightrider.services.renting.exceptions;

@SuppressWarnings("serial")
public class VehicleIsNotAvailable extends Exception {
	
	public VehicleIsNotAvailable(String msg){
		super(msg);
	}
	
	public VehicleIsNotAvailable(Exception msg){
		super(msg);
	}
}
