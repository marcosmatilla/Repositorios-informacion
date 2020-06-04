package uo.ri.persistance.administrator.training.courseattendance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;

public class CourseAttendanceGatewayImpl implements CourseAttendanceGateway {
	Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;

	}

	@Override
	public void registerNew(EnrollmentDto enrollmentDto) throws SQLException {

		String SQL_INSERT_ENROLLMENT = Conf.getInstance().getProperty("SQL_INSERT_ENROLLMENT");

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(SQL_INSERT_ENROLLMENT);

			pst.setLong(1, enrollmentDto.mechanicId);
			pst.setLong(2, enrollmentDto.courseId);
			pst.setInt(3, enrollmentDto.attendance);
			pst.setBoolean(4, enrollmentDto.passed);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void deleteAttendace(Long id) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_ATTENDANCE_BY_ID");

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long id) throws SQLException {
		List<EnrollmentDto> enrollments;
		EnrollmentDto enrollment;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_COURSE_ID");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			enrollments = new ArrayList<EnrollmentDto>();

			while (rs.next()) {
				enrollment = new EnrollmentDto();

				enrollment.mechanicId = rs.getLong("mechanic_id");
				enrollment.courseId = rs.getLong("course_id");
				enrollment.attendance = rs.getInt("attendance");
				enrollment.passed = rs.getBoolean("passed");

				enrollments.add(enrollment);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return enrollments;

	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws SQLException {
		List<CourseDto> courses;
		CourseDto course;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_ACTIVE_COURSES");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			courses = new ArrayList<CourseDto>();

			while (rs.next()) {
				course = new CourseDto();

				course.code = rs.getString("code");
				course.name = rs.getString("name");
				course.description = rs.getString("description");
				course.startDate = rs.getDate("startdate");
				course.endDate = rs.getDate("enddate");
				course.hours = rs.getInt("hours");

				courses.add(course);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return courses;
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws SQLException {
		List<MechanicDto> mechanics;
		MechanicDto mechanic;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANICS");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			mechanics = new ArrayList<MechanicDto>();

			while (rs.next()) {
				mechanic = new MechanicDto();

				mechanic.dni = rs.getString("dni");
				mechanic.name = rs.getString("name");
				mechanic.surname = rs.getString("surname");

				mechanics.add(mechanic);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return mechanics;
	}

	@Override
	public EnrollmentDto findEnrollmentSameMechanicAndCourse(Long idMechanic, Long idCourse) throws SQLException {
		EnrollmentDto enrollment = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ENROLLMENT_FOR_MECHANIC_AND_COURSE");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		try {
			pst.setLong(1, idMechanic);
			pst.setLong(2, idCourse);
			rs = pst.executeQuery();

			while (rs.next()) {
				enrollment = new EnrollmentDto();

				enrollment.id = rs.getLong("id");
				enrollment.attendance = rs.getInt("attendance");
				enrollment.passed = rs.getBoolean("passed");
				enrollment.courseId = rs.getLong("course_id");
				enrollment.mechanicId = rs.getLong("mechanic_id");

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return enrollment;
	}

	@Override
	public EnrollmentDto findCourseAttendanceById(Long courseAttendanceId) throws SQLException {
		EnrollmentDto enrollment = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_ATTENDANCE_BY_ID");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		try {

			pst.setLong(1, courseAttendanceId);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return enrollment;
			}

			enrollment = new EnrollmentDto();

			enrollment.id = rs.getLong("id");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return enrollment;
	}

}
