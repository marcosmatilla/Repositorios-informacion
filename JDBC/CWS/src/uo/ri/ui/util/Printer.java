package uo.ri.ui.util;

import java.util.List;

import alb.util.console.Console;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.dto.WorkOrderDto;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.total;
		double iva = invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice #: %d\n", invoice.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", invoice.date);
		Console.printf("\tTotal: %.2f �\n", importeSinIva);
		Console.printf("\tTax: %.1f %% \n", invoice.vat);
		Console.printf("\tTotal, tax inc.: %.2f �\n", invoice.total);
		Console.printf("\tStatus: %s\n", invoice.status);
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Available payment means");

		Console.printf("\t%s \t%-8.8s \t%s \n", "Id", "Type", "Acummulated");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean(medio);
		}
	}

	private static void printPaymentMean(PaymentMeanDto medio) {
		Console.printf("\t%d \t%-8.8s \t%s \n", medio.id, medio.getClass().getName() // not the best...
				, medio.accumulated);
	}

	public static void printWorkOrder(WorkOrderDto rep) {

		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n", rep.id, rep.description, rep.date,
				rep.status, rep.total);
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%d %-10.10s %-15.15s %-25.25s\n", m.id, m.dni, m.name, m.surname);
	}

	public static void printVehicleType(VehicleTypeDto vt) {

		Console.printf("\t%d %-10.10s %5.2f %d\n", vt.id, vt.name, vt.pricePerHour, vt.minTrainigHours);
	}

	public static void printCourse(CourseDto c) {

		Console.printf("%d\t%s %s %-35.35s %td/%<tm/%<tY %td/%<tm/%<tY %d\n", c.id, c.code, c.name, c.description,
				c.startDate, c.endDate, c.hours);
		c.percentages.forEach((id, percent) -> Console.printf("\t %d %d percent\n", id, percent));
	}

	public static void printAttendingMechanic(EnrollmentDto att) {
		Console.printf("%-30.30s\t%d\t%s\n", att.mechanic.surname + ", " + att.mechanic.name, att.attendance,
				att.passed ? "passed" : "failed");
	}

	public static void printTrainingForMechanic(TrainingForMechanicRow row) {
		Console.printf("\t%-20.20s\t%d\t%d\n", row.vehicleTypeName, row.enrolledHours, row.attendedHours);

	}

	public static void printTrainingHoursRow(TrainingHoursRow r) {
		Console.printf("%-20.20s\t%-30.30s\t%d hours\n", r.vehicleTypeName, r.mechanicFullName, r.enrolledHours);
	}

	public static void printCertificateRow(CertificateDto r) {
		Console.printf("%-20.20s\t%-30.30s\t from %td/%<tm/%<tY\n", r.vehicleType.name,
				r.mechanic.surname + ", " + r.mechanic.name, r.obtainedAt);
	}

	public static void printCertifiedMechanic(CertificateDto c) {

		Console.printf("%d\t%-10.10s %-25.25s %-25.25s\n", c.mechanic.id, c.mechanic.dni, c.mechanic.name,
				c.vehicleType.name);
	}

	public static void printWorkOrderDetail(WorkOrderDto wo) {

		Console.printf("%d for vehicle %s\n\t%-25.25s\n\t%tm/%<td/%<tY\n\t%s\n", wo.id, wo.vehicleId, wo.description,
				wo.date, wo.status);
	}

	public static void printVehicleDetail(VehicleDto v) {

		Console.printf("%d\t%-8.8s\t%s\t%s\n", v.id, v.plate, v.make, v.model);
	}

}
