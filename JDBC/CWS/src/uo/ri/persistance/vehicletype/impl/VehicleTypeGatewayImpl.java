package uo.ri.persistance.vehicletype.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class VehicleTypeGatewayImpl implements VehicleTypeGateway {
	Connection con;

	@Override
	public void setConnection(Connection con) throws SQLException {
		this.con = con;
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleType() throws SQLException {
		List<VehicleTypeDto> vehicleTypes;
		VehicleTypeDto vehicleType;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLE_TYPES");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			vehicleTypes = new ArrayList<VehicleTypeDto>();

			while (rs.next()) {
				vehicleType = new VehicleTypeDto();

				vehicleType.id = rs.getLong("id");
				vehicleType.name = rs.getString("name");
				vehicleType.pricePerHour = rs.getDouble("priceperhour");
				vehicleType.minTrainigHours = rs.getInt("mintraininghours");

				vehicleTypes.add(vehicleType);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return vehicleTypes;
	}

	@Override
	public VehicleTypeDto findById(Long id) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_TYPE_BY_ID");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		VehicleTypeDto vehicleType = null;

		try {
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return vehicleType;
			}

			vehicleType = new VehicleTypeDto();

			vehicleType.name = rs.getString("name");
			vehicleType.pricePerHour = rs.getDouble("priceperhour");
			vehicleType.minTrainigHours = rs.getInt("mintraininghours");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return vehicleType;
	}

}
