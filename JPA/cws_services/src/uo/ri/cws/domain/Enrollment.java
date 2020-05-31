package uo.ri.cws.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "MECHANIC_ID",
		"COURSE_ID" }) }, name = "TENROLLMENTS")
public class Enrollment extends BaseEntity {
	@ManyToOne
	private Mechanic mechanic;
	@ManyToOne
	private Course course;
	private int attendance;
	private boolean passed;

	public Enrollment() {

	}

	public Enrollment(Mechanic mechanic, Course course, int attendance,
			boolean passed) {
		Argument.isNotNull(mechanic);
		Argument.isNotNull(course);
		Argument.isTrue(attendance > 0);
		Argument.isTrue(!passed || (passed && attendance >= 85));
		this.mechanic = mechanic;
		this.course = course;
		this.attendance = attendance;
		this.passed = passed;
		Associations.Enroll.link(mechanic, course, this);
	}

	public int getAttendance() {
		return attendance;
	}

	public boolean isPassed() {
		return passed;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Course getCourse() {
		return course;
	}

	void _setCourse(Course course) {
		this.course = course;
	}

	public Object getAttendedHoursFor(VehicleType other) {
		Set<Dedication> hours = getCourse().getDedications();
		for (Dedication d : hours) {
			if (d.getVehicleType().equals(other)) {
				return (attendance * d.getPercentage() * getCourse().getHours())
						/ 10000;
			}
		}
		return 0;

	}

}
