package uo.ri.ui.administrator.training.report.action;

import java.util.List;

import alb.util.console.Console;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.training.CourseAttendanceService;
import uo.ri.business.serviceLayer.training.CourseCrudService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ReportsUserInteractor {

	public Long askForMechanicId() throws BusinessException {
		showMechanics();
		return Console.readLong("Mechanic id");
	}

	public String askForVehicleTypeId() throws BusinessException {
		showVehicleTypes();
		return Console.readString("Vehicle type id");
	}

	private void showVehicleTypes() throws BusinessException {
		CourseCrudService cs = ServiceFactory.getCourseCrudService();
		List<VehicleTypeDto> mechanics = cs.findAllVehicleTypes();
		Console.println("List of vehicle types");
		mechanics.forEach((vt) -> Printer.printVehicleType(vt));
	}

	private void showMechanics() throws BusinessException {
		CourseAttendanceService cs = ServiceFactory.getCourseAttendanceService();
		List<MechanicDto> mechanics = cs.findAllActiveMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m));
	}

}
