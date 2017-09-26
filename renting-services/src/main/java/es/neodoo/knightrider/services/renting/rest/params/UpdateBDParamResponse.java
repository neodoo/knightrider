package es.neodoo.knightrider.services.renting.rest.params;

public class UpdateBDParamResponse {
	
	private Boolean result;

	private String reason;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public UpdateBDParamResponse(){}

	public UpdateBDParamResponse(Boolean result, String reason) {

		this.result = result;
		this.reason = reason;

	}

}