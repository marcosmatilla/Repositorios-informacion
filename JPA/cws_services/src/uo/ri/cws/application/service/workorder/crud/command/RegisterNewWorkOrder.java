package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public class RegisterNewWorkOrder implements Command<WorkOrderDto> {
	private WorkOrderDto workOrder;

	private WorkOrderRepository repoW = Factory.repository.forWorkOrder();
	private VehicleRepository repoV = Factory.repository.forVehicle();

	public RegisterNewWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}

	@Override
	public WorkOrderDto execute() throws BusinessException {
		BusinessCheck.isNotEmpty(workOrder.vehicleId, "vehicle id is empty");
		BusinessCheck.isNotEmpty(workOrder.description, "description is empty");

		// vehicle does not exist
		Optional<Vehicle> v = repoV.findById(workOrder.vehicleId);
		BusinessCheck.exists(v, "The vehicle does not exist");

		// work order at same time
		List<WorkOrder> wo = repoW.workOrderAtSameTime(workOrder);
		BusinessCheck.isTrue(wo.isEmpty(),
				"this vehicle has another work order");

		WorkOrder w = new WorkOrder(v.get(), workOrder.description);

		repoW.add(w);

		workOrder.id = w.getId();
		workOrder.date = w.getDate();
		workOrder.description = w.getDescription();
	
		return workOrder;
	}

}
