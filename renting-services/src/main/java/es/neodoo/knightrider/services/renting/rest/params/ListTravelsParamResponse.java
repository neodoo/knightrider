package es.neodoo.knightrider.services.renting.rest.params;

import java.sql.Timestamp;

import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;

public class ListTravelsParamResponse {

	private String vin;

	private String model;

	private Timestamp date_start;

	private double latitude_start;

	private double longitude_start;

	private Timestamp date_end;

	private double latitude_end;

	private double longitude_end;

	private double cost;

	private double time;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Timestamp getDate_start() {
		return date_start;
	}

	public void setDate_start(Timestamp date_start) {
		this.date_start = date_start;
	}

	public double getLatitude_start() {
		return latitude_start;
	}

	public void setLatitude_start(double latitude_start) {
		this.latitude_start = latitude_start;
	}

	public double getLongitude_start() {
		return longitude_start;
	}

	public void setLongitude_start(double longitude_start) {
		this.longitude_start = longitude_start;
	}

	public Timestamp getDate_end() {
		return date_end;
	}

	public void setDate_end(Timestamp date_end) {
		this.date_end = date_end;
	}

	public double getLatitude_end() {
		return latitude_end;
	}

	public void setLatitude_end(double latitude_end) {
		this.latitude_end = latitude_end;
	}

	public double getLongitude_end() {
		return longitude_end;
	}

	public void setLongitude_end(double longitude_end) {
		this.longitude_end = longitude_end;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public ListTravelsParamResponse(VehicleTravel travel) {
		this.vin = travel.getVehicle().getVin();
		this.model = travel.getVehicle().getModel();
		this.date_start = travel.getDateStart();
		this.latitude_start = travel.getLatitudeStart();
		this.longitude_start = travel.getLongitudeStart();
		this.date_end = travel.getDateEnd();
		this.latitude_end = travel.getLatitudeEnd();
		this.longitude_end = travel.getLongitudeEnd();
		this.cost = travel.getCost();
		this.time = travel.getTime();
	}

	public ListTravelsParamResponse() {
	}

}
