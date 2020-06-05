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

	@Override
	public List<VehicleTypeDto> findVehicleTypeByMechanicId(Long mechanic_id) throws SQLException {
		List<VehicleTypeDto> vehicleTypes = null;
		VehicleTypeDto vehicleType = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_TYPES_FOR_MECHANIC");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, mechanic_id);
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
	public List<VehicleTypeDto> getVehicles() throws SQLException {
		List<VehicleTypeDto> res;
		VehicleTypeDto vehicle = null;

		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLETYPEID"));
		ResultSet rs = null;

		try {

			res = new ArrayList<VehicleTypeDto>();

			rs = pst.executeQuery();

			while (rs.next()) {
				vehicle = new VehicleTypeDto();
				vehicle.id = rs.getLong(1);
				vehicle.minTrainigHours = rs.getInt(2);
				vehicle.name = rs.getString(3);  
				vehicle.pricePerHour = rs.getInt(4);

				res.add(vehicle);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return res;
	}

}
