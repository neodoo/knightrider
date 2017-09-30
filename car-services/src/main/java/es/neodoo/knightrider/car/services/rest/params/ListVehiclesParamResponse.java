package es.neodoo.knightrider.car.services.rest.params;

public class ListVehiclesParamResponse {

	private String color;

	private String display_name;

	private int id;

	private String option_codes;

	private int user_id;

	private int vehicle_id;

	private String vin;

	private String[] tokens;

	private String state;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOption_codes() {
		return option_codes;
	}

	public void setOption_codes(String options_codes) {
		this.option_codes = options_codes;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getState() {
		return state;
	}
	public String[] getTokens() {
		return tokens;
	}

	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}
	public void setState(String state) {
		this.state = state;
	}


	public ListVehiclesParamResponse() {
	}
	
	public ListVehiclesParamResponse(String color, String displayName, int id,String optionCodes, int userId, int vehicleId, String vin, String[] tokens,String state ){

		this.color=color;
		this.display_name=displayName;
		this.id=id;
		this.option_codes=optionCodes;
		this.user_id =userId;
		this.vehicle_id=vehicleId;
		this.vin=vin;
		this.tokens=tokens;
		this.state=state;
	
	}

}