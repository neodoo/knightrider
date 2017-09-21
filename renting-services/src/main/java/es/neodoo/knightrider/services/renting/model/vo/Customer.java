package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String email;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Temporal(TemporalType.DATE)
	@Column(name="driving_license_date")
	private Date drivingLicenseDate;

	@Column(name="driving_license_id")
	private String drivingLicenseId;

	@Column(name="mobile")
	private String mobile;

	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="email")
	private User user;

	//bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy="customer")
	private List<CustomerAddress> customerAddresses;

	//bi-directional many-to-one association to CustomerCard
	@OneToMany(mappedBy="customer")
	private List<CustomerCard> customerCards;

	//bi-directional many-to-one association to VehicleBlocked
	@OneToMany(mappedBy="customer")
	private List<VehicleBlocked> vehicleReserveds;

	//bi-directional many-to-one association to VehicleTravel
	@OneToMany(mappedBy="customer")
	private List<VehicleTravel> vehicleTravels;

	public Customer() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDrivingLicenseDate() {
		return this.drivingLicenseDate;
	}

	public void setDrivingLicenseDate(Date drivingLicenseDate) {
		this.drivingLicenseDate = drivingLicenseDate;
	}

	public String getDrivingLicenseId() {
		return this.drivingLicenseId;
	}

	public void setDrivingLicenseId(String drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddress removeCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

	public List<CustomerCard> getCustomerCards() {
		return this.customerCards;
	}

	public void setCustomerCards(List<CustomerCard> customerCards) {
		this.customerCards = customerCards;
	}

	public CustomerCard addCustomerCard(CustomerCard customerCard) {
		getCustomerCards().add(customerCard);
		customerCard.setCustomer(this);

		return customerCard;
	}

	public CustomerCard removeCustomerCard(CustomerCard customerCard) {
		getCustomerCards().remove(customerCard);
		customerCard.setCustomer(null);

		return customerCard;
	}

	public List<VehicleBlocked> getVehicleReserveds() {
		return this.vehicleReserveds;
	}

	public void setVehicleReserveds(List<VehicleBlocked> vehicleReserveds) {
		this.vehicleReserveds = vehicleReserveds;
	}

	public VehicleBlocked addVehicleReserved(VehicleBlocked vehicleReserved) {
		getVehicleReserveds().add(vehicleReserved);
		vehicleReserved.setCustomer(this);

		return vehicleReserved;
	}

	public VehicleBlocked removeVehicleReserved(VehicleBlocked vehicleReserved) {
		getVehicleReserveds().remove(vehicleReserved);
		vehicleReserved.setCustomer(null);

		return vehicleReserved;
	}

	public List<VehicleTravel> getVehicleTravels() {
		return this.vehicleTravels;
	}

	public void setVehicleTravels(List<VehicleTravel> vehicleTravels) {
		this.vehicleTravels = vehicleTravels;
	}

	public VehicleTravel addVehicleTravel(VehicleTravel vehicleTravel) {
		getVehicleTravels().add(vehicleTravel);
		vehicleTravel.setCustomer(this);

		return vehicleTravel;
	}

	public VehicleTravel removeVehicleTravel(VehicleTravel vehicleTravel) {
		getVehicleTravels().remove(vehicleTravel);
		vehicleTravel.setCustomer(null);

		return vehicleTravel;
	}

}