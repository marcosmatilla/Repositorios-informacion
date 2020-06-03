package uo.ri.ui.administrator.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.mechanic.MechanicCrudService;
import uo.ri.conf.ServiceFactory;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		MechanicDto mechanic = new MechanicDto();

		mechanic.dni = Console.readString("Dni");
		mechanic.name = Console.readString("Name");
		mechanic.surname = Console.readString("Surname");

		MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
		mcs.addMechanic(mechanic);

		Console.println("Mechanic added");
	}

}
