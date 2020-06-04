package uo.ri.business.transactionScripts.administrator.training.courseattendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;

public class ListAttendanceByCourseId {
	private Long idCourse;
	
	public ListAttendanceByCourseId(Long idCourse) {
		this.idCourse = idCourse;
	}
	
	public List<EnrollmentDto> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cag.setConnection(c);
			cg.setConnection(c);
			if(cg.findCourseById(idCourse) == null) {
				c.rollback();
				throw new BusinessException("course does not exist");
			}
			return cag.findAttendanceByCourseId(idCourse);
		}
		catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
}
