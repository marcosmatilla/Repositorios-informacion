package uo.ri.ui.administrator.training.courseattendance.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.conf.ServiceFactory;

public class RegisterCourseAttendanceAction implements Action {

	@Override
	public void execute() throws Exception {
		EnrollmentDto e = new EnrollmentDto();
		e.mechanicId = Console.readLong("Mechanic id: ");
		e.courseId = Console.readLong("Course id: ");
		e.attendance = Console.readInt("Attendance: ");
		e.passed = Console.readString("Passed (y/n)?").equalsIgnoreCase("y");
		CourseAttendanceService cas = ServiceFactory.getCourseAttendanceService();
		cas.registerNew(e);
	}

}
