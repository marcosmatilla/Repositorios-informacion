package uo.ri.persistance.vehicletype;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;

public interface VehicleTypeGateway {

	void setConnection(Connection con) throws SQLException;

	List<VehicleTypeDto> findAllVehicleType() throws SQLException;

	VehicleTypeDto findById(Long id) throws SQLException;
	
	List<VehicleTypeDto> findVehicleTypeByMechanicId(Long mechanic_id) throws SQLException;
}
