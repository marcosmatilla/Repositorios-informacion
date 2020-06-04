package uo.ri.business.serviceLayer.administrator.training.courseattendance.impl;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.business.transactionScripts.administrator.mechanic.ListMechanic;
import uo.ri.business.transactionScripts.administrator.training.course.ListAllCourses;
import uo.ri.business.transactionScripts.administrator.training.courseattendance.DeleteCourseAttendance;
import uo.ri.business.transactionScripts.administrator.training.courseattendance.ListAttendanceByCourseId;
import uo.ri.business.transactionScripts.administrator.training.courseattendance.RegisterCourseAttendance;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
		RegisterCourseAttendance rca = new RegisterCourseAttendance(dto);
		return rca.execute();
	}

	@Override
	public void deleteAttendace(Long id) throws BusinessException {
		DeleteCourseAttendance da = new DeleteCourseAttendance(id);
		da.execute();
	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(Long id) throws BusinessException {
		ListAttendanceByCourseId labci = new ListAttendanceByCourseId(id);
		return labci.execute();
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		ListAllCourses lac = new ListAllCourses();
		return lac.execute();
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		ListMechanic lm = new ListMechanic();
		return lm.execute();
	}

}
