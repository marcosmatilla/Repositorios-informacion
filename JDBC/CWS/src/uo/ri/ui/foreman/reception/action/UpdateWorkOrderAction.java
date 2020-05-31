package uo.ri.ui.foreman.reception.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.conf.ServiceFactory;

public class UpdateWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		WorkOrderDto wo = as.findWorkOrderById(woId);

		String description = Console.readString("New description");
		wo.description = description;

		as.updateWorkOrder(wo);

		Console.println("\nUpdate done");
	}

}
