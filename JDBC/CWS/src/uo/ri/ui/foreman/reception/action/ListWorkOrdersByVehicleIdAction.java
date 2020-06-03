package uo.ri.ui.foreman.reception.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.foreman.WorkOrderService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListWorkOrdersByVehicleIdAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long id = Console.readLong("Id vehicle: ");

		WorkOrderService ws = ServiceFactory.getWorkOrderService();
		List<WorkOrderDto> wos = ws.findWorkOrdersByVehicleId(id);

		Console.println("Work orders for vehicle id " + id);
		for (WorkOrderDto wo : wos) {
			Printer.printWorkOrderDetail(wo);
		}

	}

}
