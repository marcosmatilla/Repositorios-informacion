package uo.ri.ui.administrator.training.course.action;

import java.util.Date;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;

public class UpdateCourseAction implements Action{

	@Override
	public void execute() throws Exception {
		CourseDto course = new CourseDto();
		
		course.code = Console.readString("Code");
		course.description = Console.readString("Description");
		course.endDate = askForDate("End date");
		course.hours = Console.readInt("Hours");
		course.name = Console.readString("Name");
		course.startDate = askForDate("Start date");
		
		CourseCrudService ccs = ServiceFactory.getCourseCrudService();	
		ccs.updateCourse(course);
		
		
		Console.println("Course update");
		
	}

	private Date askForDate(String msg) {
		while (true) {
			try {
				String asString = Console.readString(msg);
				return Dates.fromString(asString);
			} catch (NumberFormatException nfe) {
				Console.println("Invalid date");
			}
		}
	}
}
