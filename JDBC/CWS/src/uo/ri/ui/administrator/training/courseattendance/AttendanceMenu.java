package uo.ri.ui.administrator.training.courseattendance;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.training.courseattendance.action.DeleteCourseAttendanceAction;
import uo.ri.ui.administrator.training.courseattendance.action.ListAttendanceByCourseIdAction;
import uo.ri.ui.administrator.training.courseattendance.action.RegisterCourseAttendanceAction;

public class AttendanceMenu extends BaseMenu {

	public AttendanceMenu() {
		menuOptions = new Object[][] { { "Manager > Training management > Course Attendance", null },

				{ "Register course attendance", RegisterCourseAttendanceAction.class },
				{ "Delete course", DeleteCourseAttendanceAction.class },
				{ "List course attendance by id", ListAttendanceByCourseIdAction.class } };


	}

}
