package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the vehicle_traveling database table.
 * 
 */
@Entity
@Table(name="vehicle_traveling")
@NamedQuery(name="VehicleTraveling.findAll", query="SELECT v FROM VehicleTraveling v")
public class VehicleTraveling implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="battery_start")
	private int batteryStart;

	@Column(name="date_start")
	private Timestamp dateStart;

	@Column(name="latitude_start")
	private double latitudeStart;

	@Column(name="longitude_start")
	private double longitudeStart;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="id_vehicle")
	private Vehicle vehicle;

	public VehicleTraveling() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatteryStart() {
		return this.batteryStart;
	}

	public void setBatteryStart(int batteryStart) {
		this.batteryStart = batteryStart;
	}

	public Timestamp getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public double getLatitudeStart() {
		return this.latitudeStart;
	}

	public void setLatitudeStart(double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public double getLongitudeStart() {
		return this.longitudeStart;
	}

	public void setLongitudeStart(double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}