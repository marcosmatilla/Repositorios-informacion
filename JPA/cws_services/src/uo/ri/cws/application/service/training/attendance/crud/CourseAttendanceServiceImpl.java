package uo.ri.cws.application.service.training.attendance.crud;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.service.training.attendance.CourseAttendanceService;
import uo.ri.cws.application.service.training.attendance.crud.command.FindAllActiceCourses;
import uo.ri.cws.application.service.training.attendance.crud.command.FindAllActiveMechanics;
import uo.ri.cws.application.service.training.attendance.crud.command.ListAttendanceToCourse;
import uo.ri.cws.application.service.training.attendance.crud.command.RegisterAttendance;
import uo.ri.cws.application.service.training.attendance.crud.command.RemoveAttendance;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto)
			throws BusinessException {
		return executor.execute(new RegisterAttendance(dto));
	}

	@Override
	public void deleteAttendace(String id) throws BusinessException {
		executor.execute(new RemoveAttendance(id));

	}

	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(String id)
			throws BusinessException {
		return executor.execute(new ListAttendanceToCourse(id));
	}

	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		return executor.execute(new FindAllActiceCourses());
	}

	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		return executor.execute(new FindAllActiveMechanics());
	}

}
