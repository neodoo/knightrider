package es.neodoo.knightrider.car.services.rest.params;

public class ResponseParamVehicleCommands {
	
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

	public ResponseParamVehicleCommands(){}

	public ResponseParamVehicleCommands(Boolean result, String reason) {

		this.result = result;
		this.reason = reason;

	}

}