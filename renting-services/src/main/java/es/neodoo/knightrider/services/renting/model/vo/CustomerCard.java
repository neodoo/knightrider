package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the customer_card database table.
 * 
 */
@Entity
@Table(name="customer_card")
@NamedQuery(name="CustomerCard.findAll", query="SELECT c FROM CustomerCard c")
public class CustomerCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cvs;

	@Temporal(TemporalType.DATE)
	@Column(name="date_expired")
	private Date dateExpired;

	private String name;

	private String number;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;

	public CustomerCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCvs() {
		return this.cvs;
	}

	public void setCvs(int cvs) {
		this.cvs = cvs;
	}

	public Date getDateExpired() {
		return this.dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}