package uo.ri.business.serviceLayer.administrator.training.courseattendance.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.business.transactionScripts.administrator.training.courseattendance.RegisterCourseAttendance;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
		RegisterCourseAttendance rca = new RegisterCourseAttendance(dto);
		return rca.execute();
	}

	@Override
	public void deleteAttendace(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
