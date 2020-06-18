package uo.ri.business.transactionScripts.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;
import uo.ri.persistance.administrator.training.report.CourseReportGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class ListCertificatesByVehicleType {

	public List<CertificateDto> execute() {
		try (Connection c = Jdbc.createThreadConnection();) {
			CourseReportGateway crg = PersistenceFactory.getCourseReportGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			crg.setConnection(c);
			mg.setConnection(c);
			vtg.setConnection(c);
			List<CertificateDto> certificates = crg.findCertificatedByVehicleType();

			for (CertificateDto cer : certificates) {
				Long idMechanic = cer.mechanic.id;
				Long idVehicle = cer.vehicleType.id;
				cer.mechanic = mg.findById(idMechanic);
				cer.vehicleType = vtg.findById(idVehicle);
			}
			return certificates;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
