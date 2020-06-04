package uo.ri.business.serviceLayer.administrator.training.course.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.business.transactionScripts.administrator.training.course.RegisterCourse;

public class CourseCrudServiceImpl implements CourseCrudService{

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		RegisterCourse rs = new RegisterCourse(dto);
		return rs.execute();
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDto findCourseById(Long cId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
