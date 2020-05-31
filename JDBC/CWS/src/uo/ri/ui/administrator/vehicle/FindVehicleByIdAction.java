package uo.ri.ui.administrator.vehicle;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.vehicle.VehicleCrudService;
import uo.ri.conf.ServiceFactory;

public class FindVehicleByIdAction implements Action {

	@Override
	public void execute() throws BusinessException {
		VehicleDto vehicle = new VehicleDto();
		vehicle.id = Console.readLong("id vehicle: ");

		VehicleCrudService vcs = ServiceFactory.getVehicleCrudService();
		vcs.findVehicleById(vehicle.id);

	}

}
