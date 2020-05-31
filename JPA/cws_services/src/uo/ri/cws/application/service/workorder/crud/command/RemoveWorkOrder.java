package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class RemoveWorkOrder implements Command<Void> {
	private String workOrderId;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	public RemoveWorkOrder(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrder> wo = repo.findById(workOrderId);
		BusinessCheck.exists(wo, "The work order does not exist");

		WorkOrder w = wo.get();
		BusinessCheck.isTrue(w.getInterventions().isEmpty(),
				"this work order has an intervention");

		repo.remove(w);

		return null;
	}

}
