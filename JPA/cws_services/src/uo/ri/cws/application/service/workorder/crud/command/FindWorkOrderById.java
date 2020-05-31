package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrderById implements Command<Optional<WorkOrderDto>> {
	private String id;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	public FindWorkOrderById(String id) {
		this.id = id;
	}

	@Override
	public Optional<WorkOrderDto> execute() throws BusinessException {
		Optional<WorkOrder> wo = repo.findById(id);
		return wo.map(w -> DtoAssembler.toDto(w));
	}

}
