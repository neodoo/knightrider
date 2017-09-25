package es.neodoo.knightrider.services.renting.exceptions;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	
	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(Exception msg){
		super(msg);
	}

}
