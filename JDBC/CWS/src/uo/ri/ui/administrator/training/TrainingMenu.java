package uo.ri.ui.administrator.training;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.training.certificate.GenerateCertificatesAction;
import uo.ri.ui.administrator.training.course.CourseMenu;
import uo.ri.ui.administrator.training.courseattendance.AttendanceMenu;
import uo.ri.ui.administrator.training.report.ReportsMenu;

public class TrainingMenu extends BaseMenu {

	public TrainingMenu() {
		menuOptions = new Object[][] { { "Manager > Training management", null },

				{ "Course management", CourseMenu.class }, { "Attendance registration", AttendanceMenu.class },
				{ "Reports", ReportsMenu.class }, { "", null },
				{ "Certificate generation", GenerateCertificatesAction.class },

		};
	}

}
