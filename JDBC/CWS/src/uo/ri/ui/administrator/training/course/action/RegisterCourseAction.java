package uo.ri.ui.administrator.training.course.action;

import java.util.Date;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;

public class RegisterCourseAction implements Action{

	@Override
	public void execute() throws Exception {
		CourseDto c = new CourseDto();
		c.code = Console.readString("Course code");
		c.description = Console.readString("Description");
		c.endDate = askForDate("End date");
		c.hours = Console.readInt("Hours");
		c.name = Console.readString("Name");
		c.startDate = askForDate("Start date");
		
		CourseCrudService cs = ServiceFactory.getCourseCrudService();
		cs.registerNew(c);
		
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
