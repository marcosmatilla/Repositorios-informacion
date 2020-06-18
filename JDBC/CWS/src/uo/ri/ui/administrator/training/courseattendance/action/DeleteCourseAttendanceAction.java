package uo.ri.ui.administrator.training.courseattendance.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.conf.ServiceFactory;

public class DeleteCourseAttendanceAction implements Action {

	@Override
	public void execute() throws Exception {
		EnrollmentDto enrollmente = new EnrollmentDto();

		enrollmente.id = Console.readLong("id");

		CourseAttendanceService cas = ServiceFactory.getCourseAttendanceService();
		cas.deleteAttendace(enrollmente.id);

		Console.print("Course attendance deleted");
	}

}
