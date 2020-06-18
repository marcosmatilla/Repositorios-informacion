package uo.ri.ui.administrator.training.course.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;

public class DeleteCourseAction implements Action {

	@Override
	public void execute() throws Exception {
		CourseDto course = new CourseDto();

		course.id = Console.readLong("id");

		CourseCrudService ccs = ServiceFactory.getCourseCrudService();
		ccs.deleteCourse(course.id);

		Console.println("Course deleted");
	}

}
