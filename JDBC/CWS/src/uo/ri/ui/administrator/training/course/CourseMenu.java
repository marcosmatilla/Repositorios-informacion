package uo.ri.ui.administrator.training.course;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.training.course.action.DeleteCourseAction;
import uo.ri.ui.administrator.training.course.action.ListAllCourseAction;
import uo.ri.ui.administrator.training.course.action.ListCourseByIdAction;
import uo.ri.ui.administrator.training.course.action.RegisterCourseAction;
import uo.ri.ui.administrator.training.course.action.UpdateCourseAction;

public class CourseMenu extends BaseMenu {

	public CourseMenu() {
		menuOptions = new Object[][] { { "Manager > Training management > Courses", null },

				{ "Register course", RegisterCourseAction.class }, { "Update course", UpdateCourseAction.class },
				{ "Delete course", DeleteCourseAction.class }, { "List all courses", ListAllCourseAction.class },
				{ "List course by id", ListCourseByIdAction.class } };

	}

}
