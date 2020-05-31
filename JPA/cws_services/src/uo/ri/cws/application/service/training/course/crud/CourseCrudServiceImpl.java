package uo.ri.cws.application.service.training.course.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseCrudService;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.service.training.course.crud.command.AddCourse;
import uo.ri.cws.application.service.training.course.crud.command.FindAllVehicleTypes;
import uo.ri.cws.application.service.training.course.crud.command.FindCourseById;
import uo.ri.cws.application.service.training.course.crud.command.ListCourses;
import uo.ri.cws.application.service.training.course.crud.command.RemoveCourses;
import uo.ri.cws.application.service.training.course.crud.command.UpdateCourses;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseCrudServiceImpl implements CourseCrudService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		return executor.execute(new AddCourse(dto));
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		executor.execute(new UpdateCourses(dto));
	}

	@Override
	public void deleteCourse(String id) throws BusinessException {
		executor.execute(new RemoveCourses(id));
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		return executor.execute(new ListCourses());
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		return executor.execute(new FindAllVehicleTypes());
	}

	@Override
	public Optional<CourseDto> findCourseById(String cId)
			throws BusinessException {
		return executor.execute(new FindCourseById(cId));
	}

}
