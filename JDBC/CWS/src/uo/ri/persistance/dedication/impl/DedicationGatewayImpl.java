package uo.ri.persistance.dedication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.DedicationDto;
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

	@Override
	public int getPercentage(Long idC, Long idV) throws SQLException {
		int percentage = 1;
		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_PERCENTAGE_BY_ID"));
		ResultSet rs = null;

		try {
			pst.setLong(1, idC);
			pst.setLong(2, idV);

			rs = pst.executeQuery();

			if (!rs.next()) {
				return percentage;
			}

			percentage = rs.getInt(1);
			return percentage;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void deleteCourseFromDedication(Long idCourse) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_COURSE_IN_DEDICATION");
		try {
			pst = con.prepareStatement(SQL);

			pst.setLong(1, idCourse);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void addDedicacion(Long number, Integer ded, Long id) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_ADD_DEDICATION");
		try {
			pst = con.prepareStatement(SQL);

			pst.setLong(1, number);
			pst.setLong(2, id);
			pst.setInt(3, ded);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<DedicationDto> findAll() throws SQLException {
		List<DedicationDto> dedications;
		DedicationDto dedication;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_DEDICATIONS");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			dedications = new ArrayList<DedicationDto>();

			while (rs.next()) {
				dedication = new DedicationDto();

				dedication.id = rs.getLong("id");
				dedication.percentage = rs.getInt("percentage");
				dedication.course_id = rs.getLong("course_id");
				dedication.vehicleType_id = rs.getLong("vehicletype_id");

				dedications.add(dedication);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return dedications;
	}

	@Override
	public List<DedicationDto> findWhereCourseId(Long idCourse) throws SQLException {
		List<DedicationDto> dedications = null;
		DedicationDto d = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_DEDICATION_BY_COURSE_ID");
		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, idCourse);
			rs = pst.executeQuery();
			dedications = new ArrayList<>();
			while (rs.next()) {
				d = new DedicationDto();

				d.id = rs.getLong("id");
				d.percentage = rs.getInt("percentage");
				d.course_id = rs.getLong("course_id");
				d.vehicleType_id = rs.getLong("vehicletype_id");

				dedications.add(d);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);

		}
		return dedications;
	}

	@Override
	public DedicationDto getDedication(Long idC) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_FIND_DEDICATION_COURSE_VEHICLE");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		DedicationDto d = null;

		try {
			pst.setLong(1, idC);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return d;
			}

			d = new DedicationDto();

			d.id = rs.getLong("id");
			d.percentage = rs.getInt("percentage");
			d.course_id = rs.getLong("course_id");
			d.vehicleType_id = rs.getLong("vehicletype_id");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return d;
	}

}
