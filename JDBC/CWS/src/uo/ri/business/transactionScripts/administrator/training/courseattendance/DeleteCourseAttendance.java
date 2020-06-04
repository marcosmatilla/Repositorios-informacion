package uo.ri.business.transactionScripts.administrator.training.courseattendance;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;

public class DeleteCourseAttendance {
	private Long courseAttendanceId;

	public DeleteCourseAttendance(Long courseAttendanceId) {
		super();
		this.courseAttendanceId = courseAttendanceId;
	}

	/**
	 * Removes the attendance record specified by the id
	 * 
	 * @param id of the attendance record
	 * @throws BusinessException if the attendance record does not exist
	 */
	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			c.setAutoCommit(false);
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			cag.setConnection(c);
			if (cag.findCourseAttendanceById(courseAttendanceId) == null) {
				c.rollback();
				throw new BusinessException("enrollment does not exist");
			}
			cag.deleteAttendace(courseAttendanceId);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
