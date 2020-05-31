package uo.ri.cws.application.service.training.course.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class RemoveCourses implements Command<Void> {
	private CourseRepository repo = Factory.repository.forCourse();
	private String id;

	public RemoveCourses(String id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Course> oc = repo.findById(id);
		BusinessCheck.exists(oc, "The course does not exist");

		Course c = oc.get();
		BusinessCheck.isTrue(c.getDedications().isEmpty(),
				"course has dedications");
		BusinessCheck.isTrue(c.getEnrollments().isEmpty(),
				"course has enrollments");

		repo.remove(c);

		return null;
	}

}
