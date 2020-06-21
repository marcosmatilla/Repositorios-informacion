package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TMECHANICS")
public class Mechanic extends BaseEntity {
	@Column(unique = true)
	private String dni;

	private String surname;
	private String name;

	@OneToMany(mappedBy = "mechanic")
	private Set<WorkOrder> workorders = new HashSet<>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Intervention> interventions = new HashSet<Intervention>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Certificate> certificates = new HashSet<Certificate>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();

	 Mechanic() {

	}

	public Mechanic(String dni) {
		super();
		Argument.isNotEmpty(dni);
		Argument.isNotNull(dni);
		this.dni = dni;
	}

	public Mechanic(String dni, String surname, String name) {
		Argument.isNotEmpty(dni);
		Argument.isNotNull(dni);
		Argument.isNotEmpty(surname);
		Argument.isNotNull(surname);
		Argument.isNotEmpty(name);
		Argument.isNotNull(name);
		this.dni = dni;
		this.surname = surname;
		this.name = name;
	}

	Set<WorkOrder> _getWorkOrders() {
		return workorders;
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workorders);
	}

	public Set<WorkOrder> getAssigned() {
		return getWorkOrders();
	}

	Set<Intervention> _getInterventions() {
		return interventions;
	}

	public Set<Intervention> getInterventions() {
		return new HashSet<>(interventions);
	}

	Set<Certificate> _getCertificates() {
		return certificates;
	}

	public Set<Certificate> getCertificates() {
		return new HashSet<>(certificates);
	}

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}

	public void setInterventions(Set<Intervention> interventions) {
		this.interventions = interventions;
	}

	public String getDni() {
		return dni;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mechanic other = (Mechanic) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name="
				+ name + "]";
	}

	public Set<Enrollment> getEnrollmentsFor(VehicleType other) {
		Set<Enrollment> res = new HashSet<>();
		Set<Dedication> dedications = null;
		for (Enrollment e : getEnrollments()) {
			dedications = e.getCourse()._getDedications();
			for (Dedication d : dedications) {
				if (d.getVehicleType().equals(other)) {
					res.add(e);
				}
			}
		}
		return res;
	}

	public boolean isCertifiedFor(VehicleType other) {
		for (Certificate c : getCertificates()) {
			if (c.getVehicleType().equals(other)) {
				return true;
			}
		}
		return false;
	}

}
