package uo.ri.cws.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TCOURSES")
public class Course extends BaseEntity {

	@Column(unique = true)
	private String code;
	private String name;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private int hours;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Dedication> dedications = new HashSet<>();

	@OneToMany(mappedBy = "course")
	private Set<Enrollment> enrollments = new HashSet<>();

	public Course() {

	}

	public Course(String code) {
		Argument.isNotNull(code);
		Argument.isNotEmpty(code);
		this.code = code;
	}

	public Course(String code, String name, String description, Date startDate,
			Date endDate, int duration) {
		Argument.isNotNull(code);
		Argument.isNotEmpty(code);
		Argument.isNotNull(name);
		Argument.isNotEmpty(name);
		Argument.isNotNull(description);
		Argument.isNotEmpty(description);
		Argument.isTrue(duration > 0);
		Argument.isNotNull(startDate);
		Argument.isNotNull(endDate);
		Argument.isTrue(endDate.after(startDate));
		this.code = code;
		this.name = name;
		this.description = description;
		this.startDate = new Date();
		this.endDate = new Date();
		this.hours = duration;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Dedication> getDedications() {
		return new HashSet<>(dedications);
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}

	public void setDedications(Set<Dedication> dedications) {
		this.dedications = dedications;
	}

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}

	public void addDedications(Map<VehicleType, Integer> percentages) {
		if (!dedications.isEmpty()) {
			throw new IllegalStateException();
		}

		int res = 0;
		Collection<Integer> n = percentages.values();
		for (Integer i : n) {
			res += i;
		}
		Argument.isTrue(res == 100);
		System.out.println(res);

		Dedication d = null;
		for (Map.Entry<VehicleType, Integer> entry : percentages.entrySet()) {
			d = new Dedication(entry.getValue());
			Associations.Dedicate.link(entry.getKey(), this, d);
		}
	}

	public void clearDedications() {
		Set<Dedication> d = new HashSet<>(dedications);
		for (Dedication i : d) {
			Associations.Dedicate.unlink(i);
		}
	}

}
