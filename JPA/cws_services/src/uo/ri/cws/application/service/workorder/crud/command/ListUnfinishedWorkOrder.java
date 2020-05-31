package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class ListUnfinishedWorkOrder implements Command<List<WorkOrderDto>> {
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		List<WorkOrder> list = repo.findUnfinishedWorkOrder();

		return DtoAssembler.toWorkOrderDtoList(list);
	}

}
