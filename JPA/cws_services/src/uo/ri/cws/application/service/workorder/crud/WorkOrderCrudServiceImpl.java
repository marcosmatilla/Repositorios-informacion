package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrderById;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrderByVehicleId;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrdersByPlateNumber;
import uo.ri.cws.application.service.workorder.crud.command.RegisterNewWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.RemoveWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.UpdateWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(String woId)
			throws BusinessException {
		return executor.execute(new FindWorkOrderById(woId));
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
			throws BusinessException {
		return executor.execute(new FindWorkOrdersByPlateNumber(plate));
	}

	@Override
	public WorkOrderDto registerNew(WorkOrderDto wo) throws BusinessException {
		return executor.execute(new RegisterNewWorkOrder(wo));

	}

	@Override
	public void deleteWorkOrder(String woId) throws BusinessException {
		executor.execute(new RemoveWorkOrder(woId));

	}

	@Override
	public void updateWorkOrder(WorkOrderDto wo) throws BusinessException {
		executor.execute(new UpdateWorkOrder(wo));

	}

	/*@Override
	public List<WorkOrderDto> findAllWorkOrders() throws BusinessException {
		return executor.execute(new FindAllWorkOrders());
	}*/


	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(String id) throws BusinessException {
		return executor.execute(new FindWorkOrderByVehicleId(id));
	}

}
