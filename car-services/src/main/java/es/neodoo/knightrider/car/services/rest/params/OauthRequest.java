package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OauthRequest {

	private final static String GRANT_TYPE_PASSWORD = "password";

	private  String grant_type;

	private String client_id;

	private String client_secret;

	private  String email;

	private  String password;


	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);


		return jsonInString;

	}

	public static OauthRequest toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		OauthRequest oauthRequest = null;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		oauthRequest = mapper.readValue(jsonInString, OauthRequest.class);

		return oauthRequest;

	}

	public OauthRequest(String grant_type, String client_id, String client_secret, String email, String password ){

		this.grant_type = grant_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.email = email;
		this.password = password;

	}
	
	public OauthRequest( String clientId, String clientSecret, String email, String password ) {

		this(GRANT_TYPE_PASSWORD, clientId, clientSecret, email, password);

	}

}