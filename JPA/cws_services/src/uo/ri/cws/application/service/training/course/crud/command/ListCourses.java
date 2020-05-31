package uo.ri.cws.application.service.training.course.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class ListCourses implements Command<List<CourseDto>> {
	private CourseRepository repo = Factory.repository.forCourse();

	@Override
	public List<CourseDto> execute() throws BusinessException {
		List<CourseDto> res = new ArrayList<>();
		List<Course> courses = repo.findAll();
		for (Course c : courses) {
			CourseDto course = new CourseDto();
			course.id = c.getId();
			course.code = c.getCode();
			course.name = c.getName();
			course.description = c.getDescription();
			course.startDate = c.getStartDate();
			course.endDate = c.getEndDate();
			course.hours = c.getHours();
			res.add(course);
		}
		return res;
	}

}
