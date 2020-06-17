package uo.ri.business.transactionScripts.administrator.training.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;
import uo.ri.persistance.dedication.DedicationGateway;

public class DeleteCourse {
	private Long idCourse;

	public DeleteCourse(Long idCourse) {
		super();
		this.idCourse = idCourse;
	}

	/**
	 * A course can only be deleted if it still has no attendance registered. Delete
	 * a course also implies remove all its dedications in cascade.
	 *
	 * Note: A course and its dedications form an aggregate.
	 *
	 * @param id
	 * @throws BusinessException if: - there is no course with the specified id, or
	 *                           - the course already has enrollments registered.
	 */
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			c.setAutoCommit(false);
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			DedicationGateway dg = PersistenceFactory.getDedicationGateway();
			dg.setConnection(c);
			cag.setConnection(c);
			cg.setConnection(c);
			if (cg.findCourseById(idCourse) == null) {
				c.rollback();
				throw new BusinessException("course does not exist");
			}
			if (cag.findInEnrollment(idCourse) == null) {
				c.rollback();
				throw new BusinessException("course in enrollment");
			}
			//borrar tb de las dedicaciones
			dg.deleteCourseFromDedication(idCourse);
			c.commit();
			cg.deleteCourse(idCourse);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

}
