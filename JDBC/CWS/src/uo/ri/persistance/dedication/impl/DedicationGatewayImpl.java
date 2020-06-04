package uo.ri.persistance.dedication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistance.dedication.DedicationGateway;

public class DedicationGatewayImpl implements DedicationGateway {
	Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public int findPercentageByVehicleTypeIdAndCourseId(Long idVehicletype, Long idCourse) throws SQLException {
		int percentage = 0;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_PERCENTAGE_BY_VEHICLETYPEID_AND_COURSEID");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, idVehicletype);
			pst.setLong(2, idCourse);
			rs = pst.executeQuery();

			while (rs.next()) {

				percentage = rs.getInt("percentage");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return percentage;
	}

}
