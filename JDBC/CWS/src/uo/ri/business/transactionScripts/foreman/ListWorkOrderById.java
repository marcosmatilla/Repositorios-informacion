package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class ListWorkOrderById {
	private Long id;

	public ListWorkOrderById(Long id) {
		this.id = id;
	}

	public WorkOrderDto execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			// Comprobar que el id exista
			if (wog.findWorkOrderById(id) == null) {
				c.rollback();
				throw new BusinessException("the work orders does not exist");
			}
			return wog.findWorkOrderById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
