package uo.ri.cws.application.service.training.course.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class UpdateCourses implements Command<Void> {
	private CourseRepository repo = Factory.repository.forCourse();
	private CourseDto course;

	public UpdateCourses(CourseDto dto) {
		this.course = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		checkDto(course);

		Optional<Course> oc = repo.findById(course.id);
		BusinessCheck.exists(oc, "Course already exist");

		Course c = oc.get();
		BusinessCheck.hasVersion(c, course.version);
		c.setCode(course.code);
		c.setName(course.name);
		c.setDescription(course.description);
		c.setStartDate(course.startDate);
		c.setEndDate(course.endDate);
		c.setHours(course.hours);

		return null;
	}

	private void checkDto(CourseDto course) throws BusinessException {
		BusinessCheck.isNotEmpty(course.code, "Code is empty");
		BusinessCheck.isNotEmpty(course.description, "Descrption is empty");
		BusinessCheck.isNotEmpty(course.name, "Name is empty");
		BusinessCheck.isNotNull(course.startDate, "No start date");
		BusinessCheck.isNotNull(course.endDate, "No end date");
		BusinessCheck.isNotNull(course.percentages,
				"Course percentage is empty");
		BusinessCheck.isTrue(course.startDate.before(course.endDate));
	}

}
