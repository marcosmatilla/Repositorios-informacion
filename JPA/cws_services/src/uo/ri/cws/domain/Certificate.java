package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "MECHANIC_ID",
		"VEHICLETYPE_ID" }) }, name = "TCERTIFICATES")
public class Certificate extends BaseEntity {
	@ManyToOne
	private Mechanic mechanic;
	@ManyToOne
	private VehicleType vehicleType;
	@Temporal(TemporalType.DATE)
	private Date date;

	 Certificate() {

	}

	public Certificate(Mechanic mechanic, VehicleType vehicleType) {
		Argument.isNotNull(mechanic);
		Argument.isNotNull(vehicleType);
		this.mechanic = mechanic;
		this.vehicleType = vehicleType;
		date = new Date();
		Associations.Certify.link(this, mechanic, vehicleType);
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
