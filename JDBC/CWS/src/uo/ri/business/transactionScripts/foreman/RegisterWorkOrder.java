package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.WorkOrderGateway;

public class RegisterWorkOrder {
	private WorkOrderDto workOrder;

	public RegisterWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}

	public WorkOrderDto execute() throws BusinessException {
		WorkOrderDto aux = null;
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			if (wog.findVehicleById(workOrder.vehicleId) == null) {
				c.rollback();
				throw new BusinessException("vehicle does not exist");
			}
			if (wog.workOrderAtSameTime(workOrder)) {
				c.rollback();
				throw new BusinessException("this vehicle had another work order");
			}
			aux = wog.registerNew(workOrder);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		return aux;
	}
}
