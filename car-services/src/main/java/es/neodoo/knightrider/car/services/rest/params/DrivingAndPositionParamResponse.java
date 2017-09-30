package es.neodoo.knightrider.car.services.rest.params;

public class DrivingAndPositionParamResponse {

	private String shift_state;

	private String speed;

	private double latitude;

	private double longitude;

	private int heading; //0-359

	private int gps_as_of;

	public String getShift_state() {
		return shift_state;
	}

	public void setShift_state(String shift_state) {
		this.shift_state = shift_state;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
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

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public int getGps_as_of() {
		return gps_as_of;
	}

	public void setGps_as_of(int gps_as_of) {
		this.gps_as_of = gps_as_of;
	}

	public DrivingAndPositionParamResponse(){}

	public DrivingAndPositionParamResponse(String shiftState, String speed, double latitude, double longitude, int heading, int gpsAsOf){
	
		shift_state = shiftState;
		this.latitude=latitude;
		this.longitude=longitude;
		this.heading=heading;
		gps_as_of=gpsAsOf;

	}

}