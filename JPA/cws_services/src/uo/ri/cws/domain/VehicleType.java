package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TVEHICLETYPES")
public class VehicleType extends BaseEntity {
	@Column(unique = true)
	private String name;
	private double pricePerHour;
	private int minTrainingHours;

	@OneToMany(mappedBy = "vehicleType")
	private Set<Vehicle> vehicles = new HashSet<>();

	@OneToMany(mappedBy = "vehicleType")
	private Set<Certificate> certificates = new HashSet<>();

	@OneToMany(mappedBy = "vehicleType")
	private Set<Dedication> dedications = new HashSet<>();

	 VehicleType() {

	}

	public VehicleType(String name) {
		super();
		Argument.isNotNull(name);
		Argument.isNotEmpty(name);
		this.name = name;
	}

	public VehicleType(String name, double pricePerHour) {
		Argument.isNotNull(name);
		Argument.isNotEmpty(name);
		Argument.isNotNull(pricePerHour);
		this.name = name;
		this.pricePerHour = pricePerHour;
	}

	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}

	Set<Certificate> _getCertificates() {
		return certificates;
	}

	public Set<Certificate> getCertificates() {
		return new HashSet<>(certificates);
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}

	public Set<Dedication> getDedications() {
		return new HashSet<>(dedications);
	}

	public String getName() {
		return name;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public int getMinTrainingHours() {
		return minTrainingHours;
	}

	public void setMinTrainingHours(int minTrainingHours) {
		this.minTrainingHours = minTrainingHours;
	}

	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleType other = (VehicleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
