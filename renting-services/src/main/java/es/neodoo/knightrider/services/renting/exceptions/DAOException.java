package es.neodoo.knightrider.services.renting.exceptions;

@SuppressWarnings("serial")
public class DAOException extends Exception {
	
	public DAOException(String msg){
		super(msg);
	}
	
	public DAOException(Exception msg){
		super(msg);
	}

}
