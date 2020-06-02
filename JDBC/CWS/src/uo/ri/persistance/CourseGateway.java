package uo.ri.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;

public interface CourseGateway {
	void setConnection(Connection con);

	CourseDto registerNew(CourseDto course) throws SQLException;;

	void updateCourse(CourseDto course) throws SQLException;;

	void deleteCourse(Long id) throws SQLException;;

	List<CourseDto> findAllCourses() throws SQLException;;

	List<VehicleTypeDto> findAllVehicleTypes() throws SQLException;;

	CourseDto findCourseById(Long cId) throws SQLException;;

}
