package uo.ri.business.transactionScripts.foreman.reception;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.WorkOrderGateway;

public class ListWorkOrderById {
	private Long id;

	public ListWorkOrderById(Long id) {
		this.id = id;
	}

	public WorkOrderDto execute() {
		try (Connection c = Jdbc.createThreadConnection()) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			return wog.findWorkOrderById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
