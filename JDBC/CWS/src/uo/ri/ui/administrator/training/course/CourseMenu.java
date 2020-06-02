package uo.ri.ui.administrator.training.course;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.training.report.action.ListTrainingByVehicleTypeAction;

public class CourseMenu extends BaseMenu {

	public CourseMenu() {
		menuOptions = new Object[][] { { "Manager > Training management > Reports", null },

				{ "Register course", NotYetImplementedAction.class },
				{ "Update course", ListTrainingByVehicleTypeAction.class },
				{ "Delete course", NotYetImplementedAction.class  },
				{ "List all courses", NotYetImplementedAction.class },
				{ "List all vehicle types", NotYetImplementedAction.class  },
				{ "List course by id", NotYetImplementedAction.class } };


	}


}
