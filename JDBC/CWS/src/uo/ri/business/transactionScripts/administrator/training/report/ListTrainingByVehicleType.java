package uo.ri.business.transactionScripts.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.CourseReportGateway;

public class ListTrainingByVehicleType {
	public List<TrainingHoursRow> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseReportGateway crg = PersistenceFactory.getCourseReportGateway();
			crg.setConnection(c);
			return crg.findTrainingByVehicleTypeAndMechanic();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
