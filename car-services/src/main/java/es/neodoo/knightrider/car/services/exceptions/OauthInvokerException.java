package es.neodoo.knightrider.car.services.exceptions;

@SuppressWarnings("serial")
public class OauthInvokerException extends Exception {
	
	public OauthInvokerException(Exception msg){
		super(msg);
	}
	
	public OauthInvokerException(String msg){
		super(msg);
	}
	
}
