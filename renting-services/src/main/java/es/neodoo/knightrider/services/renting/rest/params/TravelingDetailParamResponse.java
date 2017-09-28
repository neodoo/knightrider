package es.neodoo.knightrider.services.renting.rest.params;

import java.sql.Timestamp;

import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;

public class TravelingDetailParamResponse {
	
	private int battery_start;
	
	private Timestamp date_start;

	public int getBattery_start() {
		return battery_start;
	}

	public TravelingDetailParamResponse(int battery_start, Timestamp date_start) {
		this.battery_start = battery_start;
		this.date_start = date_start;
	}

	public void setBattery_start(int battery_start) {
		this.battery_start = battery_start;
	}

	public Timestamp getDate_start() {
		return date_start;
	}

	public void setDate_start(Timestamp timestamp) {
		this.date_start = timestamp;
	}

	public TravelingDetailParamResponse() {}
	
	public TravelingDetailParamResponse (VehicleTraveling vehicleTraveling) {
		
		this.battery_start = vehicleTraveling.getBatteryStart();
		this.date_start = vehicleTraveling.getDateStart();
		
	}

}
