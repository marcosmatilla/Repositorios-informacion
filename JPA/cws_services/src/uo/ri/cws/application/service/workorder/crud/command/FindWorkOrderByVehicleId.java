package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrderByVehicleId implements Command<List<WorkOrderDto>> {
	
	private String idVehicle;
	
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();
	private VehicleRepository repoV = Factory.repository.forVehicle();

	public FindWorkOrderByVehicleId(String idVehicle) {
		this.idVehicle = idVehicle;
	}

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		BusinessCheck.isNotEmpty(this.idVehicle, "The id cannot be empty");
		BusinessCheck.isNotNull(this.idVehicle, "The id cannot be null");
		
		Optional<Vehicle> vehicle = repoV.findById(idVehicle);

		BusinessCheck.exists(vehicle, "ID does not exist");

		List<WorkOrder> wo = repo.findWorkOrderByVehicleId(idVehicle);

		return DtoAssembler.toWorkOrderDtoList(wo);
	}

}
