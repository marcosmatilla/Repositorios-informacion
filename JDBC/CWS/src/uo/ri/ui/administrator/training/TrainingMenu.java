package uo.ri.ui.administrator.training;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.training.certificate.action.GenerateCertificatesAction;
import uo.ri.ui.administrator.training.report.ReportsMenu;

public class TrainingMenu extends BaseMenu {

	public TrainingMenu() {
		menuOptions = new Object[][] { { "Manager > Training management", null },

				{ "Course management", NotYetImplementedAction.class },
				{ "Attendance registration", NotYetImplementedAction.class }, { "Reports", ReportsMenu.class },
				{ "", null }, { "Certificate generation", GenerateCertificatesAction.class },

		};
	}

}
