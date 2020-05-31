package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TDEDICATIONS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "VEHICLETYPE_ID", "COURSE_ID" }) })
public class Dedication extends BaseEntity {
	@ManyToOne
	private VehicleType vehicleType;
	@ManyToOne
	private Course course;
	private int percentage;

	Dedication() {

	}

	Dedication(int percentage) {
		this.percentage = percentage;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Course getCourse() {
		return course;
	}

	void _setCourse(Course course) {
		this.course = course;
	}

}
