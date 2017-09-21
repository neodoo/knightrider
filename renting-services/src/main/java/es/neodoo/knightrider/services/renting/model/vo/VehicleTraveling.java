package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;



/**
 * The persistent class for the vehicle_rented database table.
 * 
 */
@Entity
@Table(name="vehicle_rented")
@NamedQuery(name="VehicleTraveling.findAll", query="SELECT v FROM VehicleTraveling v")
public class VehicleTraveling implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="id_vehicle")
	private Vehicle vehicle;

	@Column(name="date_start")
	private Timestamp dateStart;

	@Column(name="battery_start")
	private int batteryStart;

	@Column(name="latitude_start")
	private double latitudeStart;

	@Column(name="longitude_start")
	private double longitudeStart;

	public double getLatitudeStart() {
		return latitudeStart;
	}

	public void setLatitudeStart(double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public double getLongitudeStart() {
		return longitudeStart;
	}

	public void setLongitudeStart(double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}

	public int getBatteryStart() {
		return batteryStart;
	}

	public void setBatteryStart(int batteryStart) {
		this.batteryStart = batteryStart;
	}

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public VehicleTraveling() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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