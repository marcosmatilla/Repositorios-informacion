package uo.ri.ui.administrator.vehicle;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.vehicle.VehicleCrudService;
import uo.ri.conf.ServiceFactory;

public class FindVehicleByPlateAction implements Action {

	@Override
	public void execute() throws BusinessException {
		VehicleDto vehicle = new VehicleDto();
		vehicle.plate = Console.readString("Plate");

		VehicleCrudService vcs = ServiceFactory.getVehicleCrudService();
		vcs.findVehicleByPlate(vehicle.plate);

	}

}
