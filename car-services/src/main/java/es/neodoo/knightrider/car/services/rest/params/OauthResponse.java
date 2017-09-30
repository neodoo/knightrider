package es.neodoo.knightrider.car.services.rest.params;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OauthResponse {

	private String access_token;

	private String token_type;

	private int expires_in;

	private String refresh_token;

	private int created_at;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String acces_token) {
		this.access_token = acces_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getCreated_at() {
		return created_at;
	}

	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

	public String toJson() throws JsonProcessingException {

		String jsonInString = null;

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		jsonInString = mapper.writeValueAsString(this);

		return jsonInString;

	}

	public static OauthResponse toObject(String jsonInString) throws JsonParseException, JsonMappingException, IOException {

		OauthResponse oauthResponse = null;

		//JSON from String to Object
		ObjectMapper mapper = new ObjectMapper();
		oauthResponse = mapper.readValue(jsonInString, OauthResponse.class);

		return oauthResponse;

	}

	public OauthResponse(){}

}