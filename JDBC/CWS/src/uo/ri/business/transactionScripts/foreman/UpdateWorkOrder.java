package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class UpdateWorkOrder {
	private WorkOrderDto workOrder;

	public UpdateWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			if (wog.findWorkOrderById(workOrder.id) == null) {
				c.rollback();
				throw new BusinessException("La averia no existe");
			}
			if (!workOrder.status.equals("OPEN") && !workOrder.status.equals("ASSIGNED")) {
				c.rollback();
				throw new BusinessException("the status of the work order is not OPEN or ASSIGN");
			}
			if(workOrder.status == null) {
				c.rollback();
				throw new BusinessException("the status of the work order can not be null");
			}
			wog.updateWorkOrder(workOrder);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
