package uo.ri.persistance.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;

public interface CourseReportGateway {
	void setConnection(Connection c);

	List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws SQLException;

	List<CertificateDto> findCertificatedByVehicleType() throws SQLException;

	List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws SQLException;
}
