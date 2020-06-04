package uo.ri.business.serviceLayer.administrator.training.course.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.business.transactionScripts.administrator.training.course.DeleteCourse;
import uo.ri.business.transactionScripts.administrator.training.course.ListAllCourses;
import uo.ri.business.transactionScripts.administrator.training.course.ListCourseById;
import uo.ri.business.transactionScripts.administrator.training.course.RegisterCourse;
import uo.ri.business.transactionScripts.administrator.training.course.UpdateCourse;
import uo.ri.business.transactionScripts.vehicletype.ListAllVehicleType;

public class CourseCrudServiceImpl implements CourseCrudService{

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		RegisterCourse rs = new RegisterCourse(dto);
		return rs.execute();
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		UpdateCourse uc = new UpdateCourse(dto);
		uc.execute();
		
	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		DeleteCourse dc = new DeleteCourse(id);
		dc.execute();		
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		ListAllCourses lac = new ListAllCourses();
		return lac.execute();
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		ListAllVehicleType lavt = new ListAllVehicleType();
		return lavt.execute();
	}

	@Override
	public CourseDto findCourseById(Long cId) throws BusinessException {
		ListCourseById lcbi = new ListCourseById(cId);
		return lcbi.execute();
	}

}
