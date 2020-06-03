package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class AssignWorkOrder {
	private Long woId;
	private Long mechanicId;

	public AssignWorkOrder(Long woId, Long mechanicId) {
		this.woId = woId;
		this.mechanicId = mechanicId;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			if (wog.findById(mechanicId) == null) {
				c.rollback();
				throw new BusinessException("mechanic does not exist");
			}
			if (wog.findWorkOrderById(woId) == null) {
				c.rollback();
				throw new BusinessException("the work orders does not exist");
			}
			if (!wog.findStatudById(woId).equals("OPEN")) {
				c.rollback();
				throw new BusinessException("status have to be OPEN");
			}
			wog.assignWorkOrderToMechanic(woId, mechanicId);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

}
