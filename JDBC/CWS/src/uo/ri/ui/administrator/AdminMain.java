package uo.ri.ui.administrator;

import alb.util.console.Printer;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.mechanic.MechanicsMenu;
import uo.ri.ui.administrator.training.TrainingMenu;

public class AdminMain {

	private static class MainMenu extends BaseMenu {
		MainMenu() {
			menuOptions = new Object[][] { { "Manager", null },

					{ "Mechanics management", MechanicsMenu.class },
					{ "Spareparts management", NotYetImplementedAction.class },
					{ "Vehicle types management", NotYetImplementedAction.class },
					{ "Training management", TrainingMenu.class }, };
		}
	}

	public static void main(String[] args) {
		new AdminMain().run().close();
	}

	private AdminMain run() {
		try {
			new MainMenu().execute();

		} catch (RuntimeException rte) {
			Printer.printRuntimeException(rte);
		}
		return this;
	}

	private void close() {

	}

}
