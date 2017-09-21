package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the vehicle_travel database table.
 * 
 */
@Entity
@Table(name="vehicle_travel")
@NamedQuery(name="VehicleTravel.findAll", query="SELECT v FROM VehicleTravel v")
public class VehicleTravel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="date_end")
	private Timestamp dateEnd;

	@Column(name="date_start")
	private Timestamp dateStart;

	@Column(name="latitude_end")
	private double latitudeEnd;

	@Column(name="latitude_start")
	private double latitudeStart;

	@Column(name="longitude_end")
	private double longitudeEnd;

	@Column(name="longitude_start")
	private double longitudeStart;
	
	@Column(name="cost")
	private double cost;
	
	@Column(name="time")
	private double time;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="id_vehicle")
	private Vehicle vehicle;

	public VehicleTravel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Timestamp getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public double getLatitudeEnd() {
		return this.latitudeEnd;
	}

	public void setLatitudeEnd(double latitudeEnd) {
		this.latitudeEnd = latitudeEnd;
	}

	public double getLatitudeStart() {
		return this.latitudeStart;
	}

	public void setLatitudeStart(double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public double getLongitudeEnd() {
		return this.longitudeEnd;
	}

	public void setLongitudeEnd(double longitudeEnd) {
		this.longitudeEnd = longitudeEnd;
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

}