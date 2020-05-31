package uo.ri.cws.application.service.workorder.crud;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateDto;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.AssignWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.ListCertifiedMechanics;
import uo.ri.cws.application.service.workorder.crud.command.ListUnfinishedWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class AssignWorkOrderServiceImpl implements AssignWorkOrderService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void assignWorkOrderToMechanic(String woId, String mId)
			throws BusinessException {
		executor.execute(new AssignWorkOrder(woId, mId));
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(String vtId)
			throws BusinessException {
		return executor.execute(new ListCertifiedMechanics(vtId));

	}

	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders()
			throws BusinessException {
		return executor.execute(new ListUnfinishedWorkOrder());
	}

}
