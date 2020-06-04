package uo.ri.business.transactionScripts.administrator.training.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;

public class ListCourseById {
	private Long idCourse;

	public ListCourseById(Long idCourse) {
		this.idCourse = idCourse;
	}
	
	public CourseDto execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			if(cg.findCourseById(idCourse) == null) {
				c.rollback();
				throw new BusinessException("course does not exist");
			}
			return cg.findCourseById(idCourse);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexción");
		}
	}
}
