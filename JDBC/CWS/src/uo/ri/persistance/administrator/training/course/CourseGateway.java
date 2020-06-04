package uo.ri.persistance.administrator.training.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;

public interface CourseGateway {
	void setConnection(Connection con);

	void registerNew(CourseDto course) throws SQLException;

	void updateCourse(CourseDto course) throws SQLException;

	void deleteCourse(Long id) throws SQLException;

	List<CourseDto> findAllCourses() throws SQLException;

	List<VehicleTypeDto> findAllVehicleTypes() throws SQLException;

	CourseDto findCourseById(Long cId) throws SQLException;

	CourseDto findCourseByName(String name) throws SQLException;

	CourseDto findInEnrollment(Long idCourse) throws SQLException;

	List<Long> findCoursesByMechanicIdAndVehicleTypeId(Long idMechanic, Long idCourse) throws SQLException;

}
