package uo.ri.ui.foreman.reception.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListWorkorderByIdAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("Id work order: ");
		WorkOrderService ws = ServiceFactory.getWorkOrderService();
		WorkOrderDto wos = ws.findWorkOrderById(id);

		Console.println("Work orders for id " + id);
		Printer.printWorkOrderDetail(wos);

	}

}
