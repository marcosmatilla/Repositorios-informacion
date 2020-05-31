package uo.ri.business.transactionScripts.administrator.vehicle;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.VehicleGateway;

public class FindVehicleByPlate {
	public String plate;

	public FindVehicleByPlate(String plate) {
		this.plate = plate;
	}

	public VehicleDto execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection()) {

			VehicleGateway vg = PersistenceFactory.getVehicleGateway();
			vg.setConnection(c);
			if (vg.findVehicleByPlate(plate) == null) {
				c.rollback();
				throw new BusinessException("La matricula no existe");
			}

			return vg.findVehicleByPlate(plate);

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}
}
