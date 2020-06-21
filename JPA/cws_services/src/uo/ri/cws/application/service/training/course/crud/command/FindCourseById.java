package uo.ri.cws.application.service.training.course.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindCourseById implements Command<Optional<CourseDto>> {
	private String id;
	private CourseRepository repo = Factory.repository.forCourse();

	public FindCourseById(String id) {
		this.id = id;
	}

	@Override
	public Optional<CourseDto> execute() throws BusinessException {
		Optional<Course> oc = repo.findById(id);
		if(!oc.isPresent()) {
			return null;
		}
		return oc.map(c -> DtoAssembler.toDto(c));
	}

}
