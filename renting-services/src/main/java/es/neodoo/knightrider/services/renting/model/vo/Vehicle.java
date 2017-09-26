package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="battery_level")
	private int batteryLevel;

	@Column(name="battery_range")
	private double batteryRange;

	private String color;

	private double latitude;

	private double longitude;
	
	@Column(name="inside_temp")
	private double insideTemp;

	@Column(name="outside_temp")
	private double outsideTemp;

	private String model;

	@Column(name="rent_state")
	private String rentState;

	private String state;

	@Column(name="sun_roof")
	private byte sunRoof;

	private String vin;

	//bi-directional many-to-one association to VehicleBlocked
	@OneToMany(mappedBy="vehicle")
	private List<VehicleBlocked> vehicleBlockeds;

	//bi-directional many-to-one association to VehicleTravel
	@OneToMany(mappedBy="vehicle")
	private List<VehicleTravel> vehicleTravels;

	//bi-directional many-to-one association to VehicleTraveling
	@OneToMany(mappedBy="vehicle")
	private List<VehicleTraveling> vehicleTravelings;

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

	public double getInsideTemp() {
		return this.insideTemp;
	}

	public void setModel(double insideTemp) {
		this.insideTemp = insideTemp;
	}
	
	public double getOutsideTemp() {
		return this.outsideTemp;
	}

	public void setOutsideTemp(double outsideTemp) {
		this.outsideTemp = outsideTemp;
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

	public byte getSunRoof() {
		return this.sunRoof;
	}

	public void setSunRoof(byte sunRoof) {
		this.sunRoof = sunRoof;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public List<VehicleBlocked> getVehicleBlockeds() {
		return this.vehicleBlockeds;
	}

	public void setVehicleBlockeds(List<VehicleBlocked> vehicleBlockeds) {
		this.vehicleBlockeds = vehicleBlockeds;
	}

	public VehicleBlocked addVehicleBlocked(VehicleBlocked vehicleBlocked) {
		getVehicleBlockeds().add(vehicleBlocked);
		vehicleBlocked.setVehicle(this);

		return vehicleBlocked;
	}

	public VehicleBlocked removeVehicleBlocked(VehicleBlocked vehicleBlocked) {
		getVehicleBlockeds().remove(vehicleBlocked);
		vehicleBlocked.setVehicle(null);

		return vehicleBlocked;
	}

	public List<VehicleTravel> getVehicleTravels() {
		return this.vehicleTravels;
	}

	public void setVehicleTravels(List<VehicleTravel> vehicleTravels) {
		this.vehicleTravels = vehicleTravels;
	}

	public VehicleTravel addVehicleTravel(VehicleTravel vehicleTravel) {
		getVehicleTravels().add(vehicleTravel);
		vehicleTravel.setVehicle(this);

		return vehicleTravel;
	}

	public VehicleTravel removeVehicleTravel(VehicleTravel vehicleTravel) {
		getVehicleTravels().remove(vehicleTravel);
		vehicleTravel.setVehicle(null);

		return vehicleTravel;
	}

	public List<VehicleTraveling> getVehicleTravelings() {
		return this.vehicleTravelings;
	}

	public void setVehicleTravelings(List<VehicleTraveling> vehicleTravelings) {
		this.vehicleTravelings = vehicleTravelings;
	}

	public VehicleTraveling addVehicleTraveling(VehicleTraveling vehicleTraveling) {
		getVehicleTravelings().add(vehicleTraveling);
		vehicleTraveling.setVehicle(this);

		return vehicleTraveling;
	}

	public VehicleTraveling removeVehicleTraveling(VehicleTraveling vehicleTraveling) {
		getVehicleTravelings().remove(vehicleTraveling);
		vehicleTraveling.setVehicle(null);

		return vehicleTraveling;
	}

}