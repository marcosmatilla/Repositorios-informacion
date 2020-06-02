package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.WorkOrderGateway;

public class ListWorkOrderByVehicleId {
	private Long id;

	public ListWorkOrderByVehicleId(Long id) {
		this.id = id;

	}

	public List<WorkOrderDto> execute() {
		try (Connection c = Jdbc.createThreadConnection()) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			return wog.findWorkOrdersByVehicleId(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
