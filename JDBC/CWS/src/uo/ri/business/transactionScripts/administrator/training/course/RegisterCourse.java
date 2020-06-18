package uo.ri.business.transactionScripts.administrator.training.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.dedication.DedicationGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class RegisterCourse {
	private CourseDto course;

	public RegisterCourse(CourseDto course) {
		super();
		this.course = course;
	}

	/**
	 * Registers a new course in the system
	 *
	 * @param dto, it must specify: name, description, startDate, endDate, hours and
	 *        the table of percentages. The id and the version fields must be null
	 *        (will be assigned by the system).
	 *
	 * @return the same Dto with the id field assigned to the created UUID
	 *
	 * @throws BusinessException, if: - any field other than id and version is null
	 *                            or empty, or - there already exists a course with
	 *                            the same name, or - there is percentage devoted to
	 *                            a non existing vehicle type, or - the initial and
	 *                            final dates are in the past or inverted, or - the
	 *                            number of hours are zero or negative, or - there
	 *                            are no dedications specified, or - the sum of
	 *                            devoted percentages does not equals 100%, or - the
	 *                            are any dedication with an invalid percentage
	 *                            (empty, zero, negative)
	 */
	public CourseDto execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			Date today = new Date();
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			if (checkfields()) { // any field (not id and version) == null or empty
				c.rollback();
				throw new BusinessException("there is a wrong field");
			}
			if (cg.findCourseByName(course.name) != null) { // existe curso con same nombre
				c.rollback();
				throw new BusinessException("already exist a course with that name");
			}
			if (!percentageDevoted()) { // there is percentage devoted to a nono existing vehicletype
				c.rollback();
				throw new BusinessException("there is percentage devoted to a non existing vehicletype");
			}
			if (course.startDate.after(course.endDate) || course.startDate.before(today)
					|| course.endDate.before(today)) { // date wrong
				c.rollback();
				throw new BusinessException("date is wrong");
			}
			if (course.hours <= 0) { // number of hours zero or negative
				c.rollback();
				throw new BusinessException("hours can not be negative or zero");
			}
			if (course.percentages.isEmpty()) { // there are not dedications specified
				c.rollback();
				throw new BusinessException("there are not dedications");
			}
			if (!sumDevotedPercentages()) { // sum of devoted percentages does not equal 100
				c.rollback();
				throw new BusinessException("sum of devoted percentages does not equals 100");
			}
			if (invalidPercentage()) { // there are any dedication with an invalid percentage (empty, zero, negative)
				c.rollback();
				throw new BusinessException("there is a dedication with and invalid percentage");
			}

			cg.registerNew(course);
			c.commit();

			CourseDto co = cg.findCourseByName(course.name);
			course.id = co.id;

			// añadir dedication
			addDedication();
			c.commit();

			return course;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private void addDedication() {
		try (Connection c = Jdbc.getConnection()) {
			DedicationGateway vg = PersistenceFactory.getDedicationGateway();
			vg.setConnection(c);
			Set<Long> keys = course.percentages.keySet();
			for (Long i : keys) {
				vg.addDedicacion(i, course.percentages.get(i), course.id);
				c.commit();
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private boolean checkfields() {
		if (course.code == null || course.description == null || course.name == null || course.endDate == null
				|| course.startDate == null || Integer.valueOf(course.hours) == null || course.code.length() == 0
				|| course.description.length() == 0 || course.name.length() == 0) {
			return true;
		}
		return false;
	}

	private boolean percentageDevoted() {
		try (Connection c = Jdbc.getConnection()) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			Set<Long> keys = course.percentages.keySet();
			for (Long i : keys) {
				if (vtg.findById(i) == null) {
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private boolean sumDevotedPercentages() {
		Set<Long> keys = course.percentages.keySet();
		int total = 0;
		for (Long i : keys)
			total += course.percentages.get(i);
		if (total == 100)
			return true;
		return false;
	}

	private boolean invalidPercentage() {
		Set<Long> keys = course.percentages.keySet();
		for (Long i : keys)
			if (course.percentages.get(i) <= 0 || course.percentages.get(i) > 100)
				return true;
		return false;
	}

}
