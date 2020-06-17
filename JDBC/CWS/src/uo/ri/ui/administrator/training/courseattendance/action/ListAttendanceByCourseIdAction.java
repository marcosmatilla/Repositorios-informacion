package uo.ri.ui.administrator.training.courseattendance.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListAttendanceByCourseIdAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("Id course");

		CourseAttendanceService cas = ServiceFactory.getCourseAttendanceService();
		List<EnrollmentDto> attendance = cas.findAttendanceByCourseId(id);

		attendance.forEach(att -> Printer.printAttendingMechanic(att));
	}

}
