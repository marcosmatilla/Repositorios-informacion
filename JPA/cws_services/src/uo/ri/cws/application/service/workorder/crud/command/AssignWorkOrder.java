package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;
import java.util.Set;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class AssignWorkOrder implements Command<Void> {
	private MechanicRepository repoM = Factory.repository.forMechanic();
	private WorkOrderRepository repoW = Factory.repository.forWorkOrder();

	private String workOrderId;
	private String mechanicId;

	public AssignWorkOrder(String workOrderId, String mechanicId) {
		this.workOrderId = workOrderId;
		this.mechanicId = mechanicId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrder> wo = repoW.findById(workOrderId);
		BusinessCheck.exists(wo, "The workorder does not exist");

		Optional<Mechanic> om = repoM.findById(mechanicId);
		BusinessCheck.exists(om, "The mechanic does not exist");

		BusinessCheck.isTrue(wo.get().getStatus().equals(WorkOrderStatus.OPEN), "work order in not open");
		
		Optional<VehicleType> v = repoW.findVehicleType(wo);

		Set<Certificate> cert = om.get().getCertificates();
		for (Certificate c : cert) {
			if (c.getVehicleType().equals(v.get())) {
				wo.get().assignTo(om.get());
				return null;
			}
		}
		throw new BusinessException("Mechanic is not certified");

	}

}
