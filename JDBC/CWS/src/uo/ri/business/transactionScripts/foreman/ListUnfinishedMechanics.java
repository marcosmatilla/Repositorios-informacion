package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class ListUnfinishedMechanics {

	public List<WorkOrderDto> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			return wog.findUnfinishedWorkOrders();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
