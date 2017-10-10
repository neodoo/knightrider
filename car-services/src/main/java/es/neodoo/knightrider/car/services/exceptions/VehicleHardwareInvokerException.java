package es.neodoo.knightrider.car.services.exceptions;

@SuppressWarnings("serial")
public class VehicleHardwareInvokerException extends Exception {
	
	public VehicleHardwareInvokerException(String msg){
		super(msg);
	}
	
	public VehicleHardwareInvokerException(Exception msg){
		super(msg);
	}

}
