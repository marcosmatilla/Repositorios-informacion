package uo.ri.ui.administrator.training.course.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListAllCourseAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("\nList of courses \n");
	
		CourseCrudService ccs = ServiceFactory.getCourseCrudService();
		List<CourseDto> courses = ccs.findAllCourses();

		for (CourseDto c : courses) {
			Printer.printCourse(c);
		}
		
	}

}
