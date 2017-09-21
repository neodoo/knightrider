package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@Table(name="vehicle")
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="battery_level")
	private int batteryLevel;

	@Column(name="battery_range")
	private double batteryRange;

	private String color;

	private double latitude;

	private double longitude;

	private String model;

	@Column(name="rent_state", columnDefinition="enum('unblocked','blocked','traveling')")
	private String rentState;
	
	@Column(name="state", columnDefinition="enum('online','offline')")
	private String state;

	@Column(name="sun_roof")
	private Boolean sunRoof;

	private String vin;

	public Vehicle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatteryLevel() {
		return this.batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public double getBatteryRange() {
		return this.batteryRange;
	}

	public void setBatteryRange(double batteryRange) {
		this.batteryRange = batteryRange;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRentState() {
		return this.rentState;
	}

	public void setRentState(String rentState) {
		this.rentState = rentState;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getSunRoof() {
		return this.sunRoof;
	}

	public void setSunRoof(Boolean sunRoof) {
		this.sunRoof = sunRoof;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

}