package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class CreateInvoiceFor implements Command<InvoiceDto> {

	private List<String> workOrderIds;
	private WorkOrderRepository wr = Factory.repository.forWorkOrder();
	private InvoiceRepository ir = Factory.repository.forInvoice();

	public CreateInvoiceFor(List<String> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		List<WorkOrder> wo = wr.findByIds(workOrderIds);
		BusinessCheck.isFalse(wo.isEmpty(), "No workorders");
		for (WorkOrder w : wo) {
			if (!w.getStatus().equals(WorkOrderStatus.FINISHED)) {
				throw new BusinessException("WorkOrder not finished");
			}
		}
		Long number = ir.getNextInvoiceNumber();

		Invoice i = new Invoice(number, wo);
		ir.add(i);

		return DtoAssembler.toDto(i);
	}

}
