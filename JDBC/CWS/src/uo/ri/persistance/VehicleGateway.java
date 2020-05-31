package uo.ri.persistance;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.dto.VehicleDto;

public interface VehicleGateway {
	void setConnection(Connection c);

	VehicleDto findVehicleByPlate(String plate) throws SQLException;

	VehicleDto findVehicleById(Long id) throws SQLException;
}
