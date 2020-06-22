package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class ListWorkOrderByVehicleId {
	private Long id;

	public ListWorkOrderByVehicleId(Long id) {
		this.id = id;

	}

	public List<WorkOrderDto> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			VehicleTypeGateway vg = PersistenceFactory.getVehicleTypeGateway();
			wog.setConnection(c);
			vg.setConnection(c);
			if(vg.findById(id) == null) {
				c.rollback();
				throw new BusinessException("the work orders does not exist");
			}
			return wog.findWorkOrdersByVehicleId(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
