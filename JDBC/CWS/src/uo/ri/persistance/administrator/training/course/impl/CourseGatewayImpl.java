package uo.ri.persistance.administrator.training.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.training.course.CourseGateway;

public class CourseGatewayImpl implements CourseGateway {
	Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;

	}

	@Override
	public CourseDto registerNew(CourseDto course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(CourseDto course) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CourseDto> findAllCourses() {
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
	public List<VehicleTypeDto> findAllVehicleTypes() throws SQLException {
		List<VehicleTypeDto> vehicleTypes;
		VehicleTypeDto vt = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLE_TYPES");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		try {
			pst =  con.prepareStatement(SQL);
			rs = pst.executeQuery();
			
			vehicleTypes = new ArrayList<VehicleTypeDto>();
			
			while(rs.next()) {
				vt = new VehicleTypeDto();
				
				vt.name = rs.getString("name");
				vt.pricePerHour = rs.getDouble("priceperhour");
				vt.minTrainigHours =  rs.getInt("minTrainingHours");
				
				vehicleTypes.add(vt);
			}
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return vehicleTypes;
	}

	@Override
	public CourseDto findCourseById(Long cId) throws SQLException {
		
		String SQL =  Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_ID");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;
		
		CourseDto co = null;
		
		try {
			pst.setLong(1, cId);
			rs = pst.executeQuery();
			
			if(rs.next() == false){
				return co;
			}
			
			co = new CourseDto();
			
			co.code = rs.getString("code");
			co.name = rs.getString("name");
			co.description = rs.getString("description");
			co.startDate = rs.getDate("startdate");
			co.endDate = rs.getDate("enddate");
			co.hours = rs.getInt("hours");
			
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
		return co;
	}

}
