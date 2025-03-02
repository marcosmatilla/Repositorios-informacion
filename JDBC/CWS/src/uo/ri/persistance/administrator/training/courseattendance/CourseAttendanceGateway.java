package uo.ri.persistance.administrator.training.courseattendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;

public interface CourseAttendanceGateway {
	void setConnection(Connection con);

	void registerNew(EnrollmentDto dto) throws SQLException;

	void deleteAttendace(Long id) throws SQLException;

	List<EnrollmentDto> findAttendanceByCourseId(Long id) throws SQLException;

	List<CourseDto> findAllActiveCourses() throws SQLException;

	List<MechanicDto> findAllActiveMechanics() throws SQLException;

	EnrollmentDto findEnrollmentSameMechanicAndCourse(Long idMechanic, Long idCourse) throws SQLException;

	EnrollmentDto findCourseAttendanceById(Long courseAttendanceId) throws SQLException;

	int findEnrollmentSameMechanicAndCourse1(Long idMechanic, Long idCourse) throws SQLException;

	int getAttendance(Long idC, Long idM) throws SQLException;

	List<EnrollmentDto> findInEnrollment(Long idCourse);

	List<EnrollmentDto> getEnrollments() throws SQLException;

	List<EnrollmentDto> mechanicInCourseAndEnroll() throws SQLException;

	List<EnrollmentDto> mechanicInEnroll(Long idMechanic) throws SQLException;

	List<EnrollmentDto> courseInEnroll(Long idCourse) throws SQLException;

}
