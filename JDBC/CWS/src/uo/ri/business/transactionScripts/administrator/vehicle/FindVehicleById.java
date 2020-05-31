package uo.ri.business.transactionScripts.administrator.vehicle;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.VehicleGateway;

public class FindVehicleById {
	private Long id;

	public FindVehicleById(Long id) {
		this.id = id;
	}

	public VehicleDto execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {
			VehicleGateway vg = PersistenceFactory.getVehicleGateway();
			vg.setConnection(c);
			if (vg.findVehicleById(id) == null) {
				c.rollback();
				throw new BusinessException("vehicle doesn't exist");
			}
			return vg.findVehicleById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
