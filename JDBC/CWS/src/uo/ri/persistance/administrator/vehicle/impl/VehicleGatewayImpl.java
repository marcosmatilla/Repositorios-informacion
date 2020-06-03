package uo.ri.persistance.administrator.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.vehicle.VehicleGateway;

public class VehicleGatewayImpl implements VehicleGateway {
	Connection con;

	@Override
	public void setConnection(Connection c) {
		this.con = c;
	}

	@Override
	public VehicleDto findVehicleByPlate(String plate) throws SQLException {

		VehicleDto vehicle;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_BY_PLATENUMBER");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, plate);
			rs = pst.executeQuery();

			vehicle = new VehicleDto();

			if (rs.next() == false) {
				return vehicle;
			}

			vehicle.id = rs.getLong("id");
			vehicle.plate = rs.getString("platenumber");
			vehicle.make = rs.getString("make");
			vehicle.model = rs.getString("model");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return vehicle;

	}

	@Override
	public VehicleDto findVehicleById(Long id) throws SQLException {
		VehicleDto vehicle;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_BY_ID");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			vehicle = new VehicleDto();

			if (rs.next() == false) {
				return vehicle;
			}

			vehicle.id = rs.getLong("id");
			vehicle.plate = rs.getString("platenumber");
			vehicle.make = rs.getString("make");
			vehicle.model = rs.getString("model");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return vehicle;
	}

}
