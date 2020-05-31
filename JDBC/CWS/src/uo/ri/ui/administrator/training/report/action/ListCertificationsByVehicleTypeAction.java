package uo.ri.ui.administrator.training.report.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.serviceLayer.training.CourseReportService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListCertificationsByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		CourseReportService rs = ServiceFactory.getCourseReportService();
		List<CertificateDto> rows = rs.findCertificatedByVehicleType();

		Console.println("Certificates by vehicle type");
		rows.forEach(r -> Printer.printCertificateRow(r));
	}

}
