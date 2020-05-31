package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
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
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();
	private CertificateRepository repoC = Factory.repository.forCerticiate();

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

		List<VehicleType> vTypes = repoV.findAll();

		List<Certificate> certificates = repoC.findAll();

		for (VehicleType v : vTypes) {
			for (Certificate c : certificates) {
				if (c.getVehicleType().equals(v)) {
					WorkOrder w = wo.get();
					Mechanic m = om.get();
					w.assignTo(m);
				} else {
					throw new BusinessException(
							"mechanic is not certified for that vehicle type");
				}
			}
		}

		BusinessCheck.isTrue(wo.get().getStatus().equals(WorkOrderStatus.OPEN),
				"work order in not open");

		return null;
	}

}
