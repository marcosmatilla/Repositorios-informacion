package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class UpdateWorkOrder implements Command<Void> {
	private WorkOrderDto workOrder;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	public UpdateWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	public Void execute() throws BusinessException {
		BusinessCheck.isNotEmpty(workOrder.id, "id is empty");
		BusinessCheck.isNotEmpty(workOrder.description, "description is empty");

		Optional<WorkOrder> wo = repo.findById(workOrder.id);
		BusinessCheck.exists(wo, "Work Order does not exist");

		BusinessCheck.isTrue(
				wo.get().getStatus().equals(WorkOrderStatus.OPEN) && wo.get()
						.getStatus().equals(WorkOrderStatus.ASSIGNED),
				"work order is not open or assign");

		WorkOrder w = wo.get();
		BusinessCheck.hasVersion(w, workOrder.version);

		w.setDescription(workOrder.description);

		return null;
	}

}
