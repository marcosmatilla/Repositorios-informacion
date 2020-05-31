package uo.ri.ui.administrator.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.conf.ServiceFactory;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		MechanicDto mechanic = new MechanicDto();

		mechanic.id = Console.readLong("id");

		MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
		mcs.deleteMechanic(mechanic.id);

		Console.println("Mechanic deleted");
	}

}
