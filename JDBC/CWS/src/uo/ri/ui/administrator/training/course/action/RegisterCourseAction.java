package uo.ri.ui.administrator.training.course.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class RegisterCourseAction implements Action {

	@Override
	public void execute() throws Exception {
		CourseDto c = new CourseDto();
		c.code = Console.readString("Course code");
		c.description = Console.readString("Description");
		c.endDate = askForDate("End date");
		c.hours = Console.readInt("Hours");
		c.name = Console.readString("Name");
		c.startDate = askForDate("Start date");

		showAllVehicleTypes();
		askDedicationPercentages(c.percentages);

		CourseCrudService cs = ServiceFactory.getCourseCrudService();
		cs.registerNew(c);

	}

	private Date askForDate(String msg) {
		while (true) {
			try {
				String asString = Console.readString(msg);
				return Dates.fromString(asString);
			} catch (NumberFormatException nfe) {
				Console.println("Invalid date");
			}
		}
	}

	private void showAllVehicleTypes() throws BusinessException {
		CourseCrudService cs = ServiceFactory.getCourseCrudService();

		List<VehicleTypeDto> vts = cs.findAllVehicleTypes();
		Console.print("Vehicle types");
		for (VehicleTypeDto vt : vts) {
			Printer.printVehicleType(vt);
		}
	}

	private void askDedicationPercentages(Map<Long, Integer> percentages) throws BusinessException {
		percentages.clear();
		int total = 0;
		while (total < 100) {
			Long vtId = Console.readLong("Vehicle type Id");
			Integer percentage = Console.readInt("Percentage");
			percentages.put(vtId, percentage);

			total += percentage;
		}
	}
}
