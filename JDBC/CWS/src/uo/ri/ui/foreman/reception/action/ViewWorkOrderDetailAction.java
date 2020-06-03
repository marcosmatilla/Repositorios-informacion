package uo.ri.ui.foreman.reception.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.foreman.WorkOrderService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ViewWorkOrderDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		WorkOrderDto wo = as.findWorkOrderById(woId);

		Printer.printWorkOrderDetail(wo);

	}
}
