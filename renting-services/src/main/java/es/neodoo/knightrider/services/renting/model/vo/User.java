package es.neodoo.knightrider.services.renting.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String password;

	//bi-directional one-to-one association to Customer
	@OneToOne(mappedBy="user")
	private Customer customer;

	//bi-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable(
		name="user_rol"
		, joinColumns={
			@JoinColumn(name="email")
			}
		, inverseJoinColumns={
			@JoinColumn(name="rol")
			}
		)
	private List<Rol> rols;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}