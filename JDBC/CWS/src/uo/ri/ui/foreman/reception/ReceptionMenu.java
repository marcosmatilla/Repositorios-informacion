package uo.ri.ui.foreman.reception;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.reception.action.AssignWorkOrderAction;
import uo.ri.ui.foreman.reception.action.ListCertifiedMechanicsAction;
import uo.ri.ui.foreman.reception.action.ListUnfinishedWorkOrdersAction;
import uo.ri.ui.foreman.reception.action.ListWorkOrdersByPlateNumberAction;
import uo.ri.ui.foreman.reception.action.ListWorkOrdersByVehicleIdAction;
import uo.ri.ui.foreman.reception.action.ListWorkorderByIdAction;
import uo.ri.ui.foreman.reception.action.RegisterWorkOrderAction;
import uo.ri.ui.foreman.reception.action.RemoveWorkOrderAction;
import uo.ri.ui.foreman.reception.action.UpdateWorkOrderAction;
import uo.ri.ui.foreman.reception.action.ViewWorkOrderDetailAction;

public class ReceptionMenu extends BaseMenu {

	public ReceptionMenu() {
		menuOptions = new Object[][] { { "Foreman > Vehicle reception", null },
				{ "Register a work order", RegisterWorkOrderAction.class },
				{ "Update a work order", UpdateWorkOrderAction.class },
				{ "Remove a work order", RemoveWorkOrderAction.class },
				{ "List work order by vehicle id", ListWorkOrdersByVehicleIdAction.class },
				{ "List unfinished work orders", ListUnfinishedWorkOrdersAction.class },
				{ "List work orders by plate", ListWorkOrdersByPlateNumberAction.class },
				{ "View work order detail", ViewWorkOrderDetailAction.class },
				{ "List work order by id", ListWorkorderByIdAction.class },
				{ "List certified mechanics", ListCertifiedMechanicsAction.class },
				{ "Assign a work order", AssignWorkOrderAction.class }, };
	}

}
