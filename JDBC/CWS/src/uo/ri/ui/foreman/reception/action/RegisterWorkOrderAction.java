package uo.ri.ui.foreman.reception.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.conf.ServiceFactory;

public class RegisterWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		WorkOrderDto w = new WorkOrderDto();
		w.vehicleId = Console.readLong("vehicle id: ");

		w.description = Console.readString("type a description of the problem: ");

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		as.registerNew(w);

	}

}
