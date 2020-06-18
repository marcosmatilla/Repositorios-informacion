package uo.ri.business.transactionScripts.administrator.training.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;

public class ListAllCourses {

	public List<CourseDto> execute() {
		try (Connection c = Jdbc.createThreadConnection();) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findAllCourses();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexción");
		}
	}

}
