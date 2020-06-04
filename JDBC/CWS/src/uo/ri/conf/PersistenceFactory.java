package uo.ri.conf;

import uo.ri.persistance.administrator.mechanic.MechanicGateway;
import uo.ri.persistance.administrator.mechanic.impl.MechanicGatewayImpl;
import uo.ri.persistance.administrator.training.certificate.CertificateGateway;
import uo.ri.persistance.administrator.training.certificate.impl.CertificateGatewayImpl;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.course.impl.CourseGatewayImpl;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;
import uo.ri.persistance.administrator.training.courseattendance.impl.CourseAttendanceGatewayImpl;
import uo.ri.persistance.administrator.training.report.CourseReportGateway;
import uo.ri.persistance.administrator.training.report.impl.CourseReportGatewayImpl;
import uo.ri.persistance.administrator.vehicle.VehicleGateway;
import uo.ri.persistance.administrator.vehicle.impl.VehicleGatewayImpl;
import uo.ri.persistance.cashier.InvoiceGateway;
import uo.ri.persistance.cashier.impl.InvoiceGatewayImpl;
import uo.ri.persistance.foreman.WorkOrderGateway;
import uo.ri.persistance.foreman.impl.WorkOrderGatewayImpl;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;
import uo.ri.persistance.vehicletype.impl.VehicleTypeGatewayImpl;

public class PersistenceFactory {
	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}

	public static InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}

	public static CertificateGateway getCertificateGateway() {
		return new CertificateGatewayImpl();
	}

	public static CourseReportGateway getCourseReportGateway() {
		return new CourseReportGatewayImpl();
	}

	public static VehicleGateway getVehicleGateway() {
		return new VehicleGatewayImpl();
	}

	public static WorkOrderGateway getWorkOrderGateway() {
		return new WorkOrderGatewayImpl();
	}
	
	public static CourseGateway getCourseGateway() {
		return new CourseGatewayImpl();
	}
	
	public static CourseAttendanceGateway getCourseAttendanceGateway() {
		return new CourseAttendanceGatewayImpl();
	}
	
	public static VehicleTypeGateway getVehicleTypeGateway() {
		return new VehicleTypeGatewayImpl();
	}
}
