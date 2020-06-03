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
	public EnrollmentDto registerNew(EnrollmentDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAttendace(Long id) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_ATTENDANCE_BY_ID");
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			pst.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
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
			
			while(rs.next()) {
				enrollment = new EnrollmentDto();
				
				enrollment.mechanicId = rs.getString("mechanic_id");
				enrollment.courseId = rs.getString("course_id");
				enrollment.attendance = rs.getInt("attendance");
				enrollment.passed = rs.getBoolean("passed");	
				
				enrollments.add(enrollment);
			}
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
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
			
			while(rs.next()) {
				course = new CourseDto();
				
				course.code = rs.getString("code");
				course.name = rs.getString("name");
				course.description = rs.getString("description");
				course.startDate = rs.getDate("startdate");
				course.endDate = rs.getDate("enddate");
				course.hours = rs.getInt("hours");
				
				courses.add(course);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return courses;
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws SQLException {
		List<MechanicDto> mechanics;
		MechanicDto mechanic;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_ACTIVE_MECHANICS");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();
			
			mechanics = new ArrayList<MechanicDto>();
			
			while(rs.next()) {
				mechanic = new MechanicDto();
			
				mechanic.dni = rs.getString("dni");
				mechanic.name = rs.getString("name");
				mechanic.surname = rs.getString("surname");
				
				mechanics.add(mechanic);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst);
		}
		return mechanics;
	}

}
