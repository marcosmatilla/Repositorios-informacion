package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TCLIENTS")
public class Client extends BaseEntity {
	@Column(unique = true)
	private String dni;

	private String name;
	private String surname;
	private String email;
	private String phone;

	private Address address;

	@OneToMany(mappedBy = "client")
	private Set<Vehicle> vehicles = new HashSet<>();

	@OneToMany(mappedBy = "client")
	private Set<PaymentMean> paymentMeans = new HashSet<>();

	public Client() {

	}

	public Client(String dni) {
		super();
		Argument.isNotNull(dni);
		this.dni = dni;
	}

	public Client(String dni, String name, String surname) {
		Argument.isNotNull(dni);
		this.dni = dni;
		Argument.isNotNull(name);
		this.name = name;
		Argument.isNotNull(surname);
		this.surname = surname;
	}

	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}

	Set<PaymentMean> _getPaymentMeans() {
		return paymentMeans;
	}

	public Set<PaymentMean> getPaymentMeans() {
		return new HashSet<>(paymentMeans);
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", phone=" + phone + ", address="
				+ address + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
}
