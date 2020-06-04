package uo.ri.ui.administrator.training.courseattendance;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.training.courseattendance.action.RegisterCourseAttendanceAction;

public class AttendanceMenu extends BaseMenu {

	public AttendanceMenu() {
		menuOptions = new Object[][] { { "Manager > Training management > Course Attendance", null },

				{ "Register course attendance", RegisterCourseAttendanceAction.class },
				{ "Delete course", NotYetImplementedAction.class },
				{ "List all courses", NotYetImplementedAction.class },
				{ "List course attendance by id", NotYetImplementedAction.class } };


	}

}
