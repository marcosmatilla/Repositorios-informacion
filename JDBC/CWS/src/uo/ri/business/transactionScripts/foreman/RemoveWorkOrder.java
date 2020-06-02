package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.WorkOrderGateway;

public class RemoveWorkOrder {
	private Long idWorkOrder;

	public RemoveWorkOrder(Long idWorkOrder) {
		super();
		this.idWorkOrder = idWorkOrder;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			if (wog.findInInteventions(idWorkOrder) != null) {
				c.rollback();
				throw new BusinessException("work order to delete have an intervention open");
			}
			if (wog.findWorkOrderById(idWorkOrder) == null) {
				c.rollback();
				throw new BusinessException("the id does not exist");
			}
			wog.deleteWorkOrder(idWorkOrder);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
