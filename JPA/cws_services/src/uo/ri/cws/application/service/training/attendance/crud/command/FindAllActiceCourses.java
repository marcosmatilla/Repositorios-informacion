package uo.ri.cws.application.service.training.attendance.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindAllActiceCourses implements Command<List<CourseDto>> {
	private EnrollmentRepository repo = Factory.repository.forEnrollment();

	@Override
	public List<CourseDto> execute() throws BusinessException {
		List<CourseDto> res = new ArrayList<>();

		List<Course> courses = repo.findAllActiveCourses();
		for (Course c : courses) {
			CourseDto course = new CourseDto();
			course.code = c.getCode();
			course.description = c.getDescription();
			course.endDate = c.getEndDate();
			course.startDate = c.getStartDate();
			course.hours = c.getHours();
			course.id = c.getId();
			course.name = c.getName();
			res.add(course);
		}
		return res;
	}

}
