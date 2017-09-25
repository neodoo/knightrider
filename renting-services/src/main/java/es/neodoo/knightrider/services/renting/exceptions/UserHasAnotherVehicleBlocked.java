package es.neodoo.knightrider.services.renting.exceptions;

@SuppressWarnings("serial")
public class UserHasAnotherVehicleBlocked extends Exception {
	
	public UserHasAnotherVehicleBlocked(String msg){
		super(msg);
	}
	
	public UserHasAnotherVehicleBlocked(Exception msg){
		super(msg);
	}

}
