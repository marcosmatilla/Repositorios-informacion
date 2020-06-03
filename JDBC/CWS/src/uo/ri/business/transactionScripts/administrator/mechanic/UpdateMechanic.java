package uo.ri.business.transactionScripts.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;

public class UpdateMechanic {

	public MechanicDto mechanic;

	public UpdateMechanic(MechanicDto mechanic) {
		this.mechanic = mechanic;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);
			if (mg.findById(mechanic.id) == null) {
				c.rollback();
				throw new BusinessException("El usuario no existe");
			}
			mg.update(mechanic);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
