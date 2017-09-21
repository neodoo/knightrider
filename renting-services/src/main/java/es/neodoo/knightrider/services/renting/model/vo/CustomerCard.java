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

	@EmbeddedId
	private CustomerCardPK id;

	private Boolean active;

	private int cvs;

	@Temporal(TemporalType.DATE)
	@Column(name="date_expired")
	private Date dateExpired;

	private String name;

	private String number;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="email", insertable=false, updatable=false)
	private Customer customer;

	public CustomerCard() {
	}

	public CustomerCardPK getId() {
		return this.id;
	}

	public void setId(CustomerCardPK id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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