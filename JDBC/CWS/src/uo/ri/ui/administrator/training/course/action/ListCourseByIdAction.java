package uo.ri.ui.administrator.training.course.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListCourseByIdAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("Id course");
		CourseCrudService ccs = ServiceFactory.getCourseCrudService();
		CourseDto cd = ccs.findCourseById(id);

		Console.println("Course for id " + id);
		Printer.printCourse(cd);
	}

}
