package uo.ri.business.transactionScripts.vehicletype;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class ListAllVehicleType {
	public List<VehicleTypeDto> execute() {
		try (Connection c = Jdbc.createThreadConnection();) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			return vtg.findAllVehicleType();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}
}
