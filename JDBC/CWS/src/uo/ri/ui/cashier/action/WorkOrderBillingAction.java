package uo.ri.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.cashier.InvoiceService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class WorkOrderBillingAction implements Action {
	@Override
	public void execute() throws BusinessException {
		
		List<Long> workOrderIds = new ArrayList<Long>();
		
		do {
			Long id = Console.readLong("Type work order ids ? ");
			workOrderIds.add(id);
		} while (nextWorkorder());
		InvoiceService invoiceServiceImpl = ServiceFactory.getInvoiceService();
		Printer.printInvoice(invoiceServiceImpl.createInvoiceFor(workOrderIds));
	}

	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}

}
