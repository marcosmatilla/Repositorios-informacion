package uo.ri.persistance.dedication;

import java.sql.Connection;
import java.sql.SQLException;

public interface DedicationGateway {
	void setConnection(Connection con);

	int findPercentageByVehicleTypeIdAndCourseId(Long idVehicletype, Long idCourse) throws SQLException;
	
	int getPercentage(Long idC, Long idV) throws SQLException;
}
