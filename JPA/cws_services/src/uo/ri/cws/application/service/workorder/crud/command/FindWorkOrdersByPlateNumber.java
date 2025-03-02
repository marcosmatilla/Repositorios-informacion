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

public class FindWorkOrdersByPlateNumber implements Command<List<WorkOrderDto>> {
	private String plateNumber;
	private WorkOrderRepository repoW = Factory.repository.forWorkOrder();
	private VehicleRepository repoV = Factory.repository.forVehicle();

	public FindWorkOrdersByPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Override
	public List<WorkOrderDto> execute() throws BusinessException {
		BusinessCheck.isNotEmpty(this.plateNumber, "The plate cannot be empty");
		BusinessCheck.isNotNull(this.plateNumber, "The plate cannot be null");
		Optional<Vehicle> vehicle = repoV.findByPlate(plateNumber);

		BusinessCheck.exists(vehicle, "plate number does not exist");

		List<WorkOrder> wo = repoW.findByPlateNumber(plateNumber);

		return DtoAssembler.toWorkOrderDtoList(wo);
	}

}
