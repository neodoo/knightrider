package es.neodoo.knightrider.services.renting.rest.params;

import es.neodoo.knightrider.services.renting.model.vo.Vehicle;

public class VehicleParamResponse {
	
	private Integer id_vehicle;
	
	private String state;

	private String rent_state;
	
	private String color;
	
	private String vin;
	
	private int battery_level;
	
	private double battery_range;
	
	private double latitude;
	
	private double longitude;
	
	private double inside_temp;
	
	private double outside_temp;
	
	private Boolean sun_roof;
	
	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getId_vehicle() {
		return id_vehicle;
	}

	public void setId_vehicle(Integer id_vehicle) {
		this.id_vehicle = id_vehicle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRent_state() {
		return rent_state;
	}

	public void setRent_state(String rent_state) {
		this.rent_state = rent_state;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getBattery_level() {
		return battery_level;
	}

	public void setBattery_level(int battery_level) {
		this.battery_level = battery_level;
	}

	public double getBattery_range() {
		return battery_range;
	}

	public void setBattery_range(double battery_range) {
		this.battery_range = battery_range;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getInside_temp() {
		return inside_temp;
	}

	public void setInside_temp(double inside_temp) {
		this.inside_temp = inside_temp;
	}

	public double getOutside_temp() {
		return outside_temp;
	}

	public void setOutside_temp(double outside_temp) {
		this.outside_temp = outside_temp;
	}

	public Boolean getSun_roof() {
		return sun_roof;
	}

	public void setSun_roof(Boolean sun_roof) {
		this.sun_roof = sun_roof;
	}

	public VehicleParamResponse(Vehicle vehicle) {
		super();
		this.id_vehicle = vehicle.getId();
		this.state = vehicle.getState();
		this.rent_state = vehicle.getRentState();
		this.color = vehicle.getColor();
		this.vin = vehicle.getVin();
		this.battery_level = vehicle.getBatteryLevel();
		this.battery_range = vehicle.getBatteryRange();
		this.latitude = vehicle.getLatitude();
		this.longitude = vehicle.getLongitude();
		this.inside_temp = vehicle.getInsideTemp();
		this.outside_temp = vehicle.getOutsideTemp();
		this.sun_roof = convertByteToBoolean(vehicle.getSunRoof());
		this.model = vehicle.getModel();
	}

	public VehicleParamResponse() {
	}
	
	private Boolean convertByteToBoolean(byte myByte){
		
		 boolean myBool = (myByte!=0);
		 
		 return myBool;
		
	}
	
}
