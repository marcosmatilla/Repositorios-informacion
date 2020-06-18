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

public class UpdateCourseAction implements Action {

	@Override
	public void execute() throws Exception {
		CourseDto course = new CourseDto();

		course.id = Console.readLong("Course id");

		course.code = Console.readString("Code");
		course.description = Console.readString("Description");
		course.endDate = askForDate("End date");
		course.hours = Console.readInt("Hours");
		course.name = Console.readString("Name");
		course.startDate = askForDate("Start date");
		showAllVehicleTypes();
		askDedicationPercentages(course.percentages);
		CourseCrudService ccs = ServiceFactory.getCourseCrudService();
		ccs.updateCourse(course);

		Console.println("Course update");

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
}
