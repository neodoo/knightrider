package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehicle_blocked database table.
 * 
 */
@Entity
@Table(name="vehicle_blocked")
@NamedQuery(name="VehicleBlocked.findAll", query="SELECT v FROM VehicleBlocked v")
public class VehicleBlocked implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="id_vehicle")
	private Vehicle vehicle;

	public VehicleBlocked() {
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