package uo.ri.persistance.administrator.training.course.impl;

import java.sql.Connection;
import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CourseDto findCourseById(Long cId) {
		// TODO Auto-generated method stub
		return null;
	}

}
