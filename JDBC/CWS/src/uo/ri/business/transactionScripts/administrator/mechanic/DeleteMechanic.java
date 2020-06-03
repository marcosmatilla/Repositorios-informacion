package uo.ri.business.transactionScripts.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;

public class DeleteMechanic {
	private Long idMechanic;

	public DeleteMechanic(Long idMechanic) {
		super();
		this.idMechanic = idMechanic;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			c.setAutoCommit(false);
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);
			if (mg.findById(idMechanic) == null) {
				c.rollback();
				throw new BusinessException("mechanic does not exist");
			}
			if (mg.findInEnrollment(idMechanic) != null) {
				c.rollback();
				throw new BusinessException("mechanic in enrollment");
			}
			mg.delete(idMechanic);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
