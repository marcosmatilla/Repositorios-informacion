package uo.ri.conf;

import uo.ri.business.serviceLayer.administrator.mechanic.MechanicCrudService;
import uo.ri.business.serviceLayer.administrator.mechanic.impl.MechanicCrudServiceImpl;
import uo.ri.business.serviceLayer.administrator.training.certificate.CertificateService;
import uo.ri.business.serviceLayer.administrator.training.certificate.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.administrator.training.course.CourseCrudService;
import uo.ri.business.serviceLayer.administrator.training.course.impl.CourseCrudServiceImpl;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.CourseAttendanceService;
import uo.ri.business.serviceLayer.administrator.training.courseattendance.impl.CourseAttendanceServiceImpl;
import uo.ri.business.serviceLayer.administrator.training.report.CourseReportService;
import uo.ri.business.serviceLayer.administrator.training.report.impl.CourseReportServiceImpl;
import uo.ri.business.serviceLayer.administrator.vehicle.VehicleCrudService;
import uo.ri.business.serviceLayer.administrator.vehicle.impl.VehicleCrudServiceImpl;
import uo.ri.business.serviceLayer.cashier.InvoiceService;
import uo.ri.business.serviceLayer.cashier.impl.InvoiceServiceImpl;
import uo.ri.business.serviceLayer.foreman.WorkOrderService;
import uo.ri.business.serviceLayer.foreman.impl.WorkOrderServiceImpl;

public class ServiceFactory {
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	public static InvoiceService getInvoiceService() {
		return new InvoiceServiceImpl();
	}

	public static CertificateService getCertificateService() {
		return new CertificateServiceImpl();
	}

	public static CourseReportService getCourseReportService() {
		return new CourseReportServiceImpl();
	}

	public static VehicleCrudService getVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}

	public static WorkOrderService getWorkOrderService() {
		return new WorkOrderServiceImpl();
	}
	
	public static CourseCrudService getCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

	public static CourseAttendanceService getCourseAttendanceService() {
		return new CourseAttendanceServiceImpl();
	}
}
