package uo.ri.conf;

import uo.ri.persistance.CertificateGateway;
import uo.ri.persistance.CourseReportGateway;
import uo.ri.persistance.InvoiceGateway;
import uo.ri.persistance.MechanicGateway;
import uo.ri.persistance.VehicleGateway;
import uo.ri.persistance.WorkOrderGateway;
import uo.ri.persistance.impl.CertificateGatewayImpl;
import uo.ri.persistance.impl.CourseReportGatewayImpl;
import uo.ri.persistance.impl.InvoiceGatewayImpl;
import uo.ri.persistance.impl.MechanicGatewayImpl;
import uo.ri.persistance.impl.VehicleGatewayImpl;
import uo.ri.persistance.impl.WorkOrderGatewayImpl;

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
}
