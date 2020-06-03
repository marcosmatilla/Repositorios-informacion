package uo.ri.persistance.administrator.training.courseattendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;

public interface CourseAttendanceGateway {
	void setConnection(Connection con);

	EnrollmentDto registerNew(EnrollmentDto dto) throws SQLException;

	void deleteAttendace(Long id) throws SQLException;

	List<EnrollmentDto> findAttendanceByCourseId(Long id) throws SQLException;

	List<CourseDto> findAllActiveCourses() throws SQLException;

	List<MechanicDto> findAllActiveMechanics() throws SQLException;
}
