package uo.ri.ui.foreman.reception.action;

import alb.util.console.Console;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.vehicle.VehicleCrudService;
import uo.ri.conf.ServiceFactory;

public class WorkOrderUserInteractor {

	public WorkOrderDto askForWorkOrder(VehicleDto v) {
		WorkOrderDto wo = new WorkOrderDto();
		wo.description = Console.readString("Work description");
		wo.vehicleId = v.id;
		return wo;
	}

	public VehicleDto askForVehicle() throws BusinessException {
		Long vehicle_id = Console.readLong("vehicle id");

		VehicleCrudService vs = ServiceFactory.getVehicleCrudService();
		VehicleDto ov = vs.findVehicleById(vehicle_id);
		return ov;
	}

}
