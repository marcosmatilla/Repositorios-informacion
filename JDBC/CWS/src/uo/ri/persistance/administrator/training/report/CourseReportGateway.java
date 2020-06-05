package uo.ri.persistance.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CertificateDto;

public interface CourseReportGateway {
	void setConnection(Connection c);
	
	List<CertificateDto> findCertificatedByVehicleType() throws SQLException;

}
