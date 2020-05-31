package uo.ri.business.serviceLayer.workorder.impl;

import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.workorder.WorkOrderService;
import uo.ri.business.transactionScripts.foreman.reception.AssignWorkOrder;
import uo.ri.business.transactionScripts.foreman.reception.ListCertifiedMechanics;
import uo.ri.business.transactionScripts.foreman.reception.ListUnfinishedMechanics;
import uo.ri.business.transactionScripts.foreman.reception.ListWorkOrderById;
import uo.ri.business.transactionScripts.foreman.reception.ListWorkOrderByVehicleId;
import uo.ri.business.transactionScripts.foreman.reception.ListWorkOrdersByPlateNumber;
import uo.ri.business.transactionScripts.foreman.reception.RegisterWorkOrder;
import uo.ri.business.transactionScripts.foreman.reception.RemoveWorkOrder;
import uo.ri.business.transactionScripts.foreman.reception.UpdateWorkOrder;

public class WorkOrderServiceImpl implements WorkOrderService {

	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		RegisterWorkOrder rwo = new RegisterWorkOrder(dto);
		return rwo.execute();
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		UpdateWorkOrder uwo = new UpdateWorkOrder(dto);
		uwo.execute();
	}

	@Override
	public void deleteWorkOrder(Long id) throws BusinessException {
		RemoveWorkOrder rwo = new RemoveWorkOrder(id);
		rwo.execute();

	}

	@Override
	public WorkOrderDto findWorkOrderById(Long woId) throws BusinessException {
		ListWorkOrderById lwobi = new ListWorkOrderById(woId);
		return lwobi.execute();
	}

	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException {
		ListUnfinishedMechanics lufm = new ListUnfinishedMechanics();
		return lufm.execute();
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) throws BusinessException {
		ListWorkOrderByVehicleId lwobvi = new ListWorkOrderByVehicleId(id);
		return lwobvi.execute();
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		ListWorkOrdersByPlateNumber lwobpn = new ListWorkOrdersByPlateNumber(plate);
		return lwobpn.execute();
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(Long id) throws BusinessException {
		ListCertifiedMechanics lcm = new ListCertifiedMechanics(id);
		return lcm.execute();
	}

	@Override
	public void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws BusinessException {
		AssignWorkOrder awo = new AssignWorkOrder(woId, mechanicId);
		awo.execute();

	}

}
