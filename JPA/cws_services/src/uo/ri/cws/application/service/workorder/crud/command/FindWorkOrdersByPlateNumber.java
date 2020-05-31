package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrdersByPlateNumber
		implements Command<List<WorkOrderDto>> {
	private String plateNumber;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	public FindWorkOrdersByPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		List<WorkOrder> wo = repo.findByPlateNumber(plateNumber);
		return DtoAssembler.toWorkOrderDtoList(wo);
	}

}
