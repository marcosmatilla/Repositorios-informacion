package uo.ri.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.TrainingHoursRow;

public interface CourseReportGateway {
	void setConnection(Connection c);

	List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws SQLException;
}
