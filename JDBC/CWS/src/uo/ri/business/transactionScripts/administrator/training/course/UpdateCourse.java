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
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class UpdateCourse {
	CourseDto course;

	public UpdateCourse(CourseDto course) {
		this.course = course;
	}

	/**
	 * Updates the course specified by the id with the new data. A course an only be
	 * modified if it has not yet started. If the start date is wrong then remove
	 * the course and start again... The dedications of the course to the vehicle
	 * types are not modified by this operation.
	 *
	 * @param dto. Must specify all the fields. The id and version fields must match
	 *        the current state of the course. All the rest of fields must be
	 *        filled, even if there is no change in the data. So it must pass the
	 *        same validation as for new courses.
	 *
	 * @throws BusinessException if: - it does not exist the course with the
	 *                           specified id, or - the current version of the
	 *                           course does not match the version of the dto, or -
	 *                           the course has its start date in the past, or - the
	 *                           new data does not pass the validation specified
	 *                           in @see registerNew
	 *
	 */
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			Date today = new Date();
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);

			if (cg.findCourseById(course.id) == null) { // no existe curso con id
				c.rollback();
				throw new BusinessException("there is not a course with that id");
			}
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
			cg.updateCourse(course);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
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
