package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class ViewWorkOrderDetail {

	private Long id;

	public ViewWorkOrderDetail(Long id) {
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
