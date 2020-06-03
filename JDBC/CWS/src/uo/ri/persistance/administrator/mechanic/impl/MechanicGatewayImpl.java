package uo.ri.persistance.administrator.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void add(MechanicDto mechanic) throws SQLException {

		String SQL = Conf.getInstance().getProperty("SQL_INSERT_MECHANICS");
		// Process
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.dni);
			pst.setString(2, mechanic.name);
			pst.setString(3, mechanic.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void delete(Long idMechanic) throws SQLException {

		String SQL = Conf.getInstance().getProperty("SQL_DELETE_MECHANIC_BY_ID");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void update(MechanicDto mechanic) throws SQLException {

		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.name);
			pst.setString(2, mechanic.surname);
			pst.setLong(3, mechanic.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public List<MechanicDto> findAll() throws SQLException {

		List<MechanicDto> mechanics;
		MechanicDto mechanic;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANICS");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			mechanics = new ArrayList<MechanicDto>(); // lista a devolver

			while (rs.next()) {

				mechanic = new MechanicDto(); // Dto, hay un mechanic. por cada uno de los atriubutos del Dto de listar
												// mecánicos
				mechanic.id = rs.getLong("id");
				mechanic.dni = rs.getString("dni");
				mechanic.name = rs.getString("name");
				mechanic.surname = rs.getString("surname");
				mechanics.add(mechanic); // Añado mecánicos a la lista

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return mechanics;
	}

	@Override
	public MechanicDto findByDni(String dni) throws SQLException {

		MechanicDto mechanic = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_BY_DNI");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return mechanic;

			mechanic = new MechanicDto(); // Dto, hay un mechanic. por cada uno de los atriubutos del Dto de lista
											// mecánicos
			mechanic.id = rs.getLong("id");
			mechanic.dni = rs.getString("dni");
			mechanic.name = rs.getString("name");
			mechanic.surname = rs.getString("surname");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return mechanic;

	}

	@Override
	public MechanicDto findById(Long idMechanic) throws SQLException {
		MechanicDto mechanic = null;

		PreparedStatement pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_MECHANIC_BY_ID"));
		ResultSet rs = null;

		try {

			pst.setLong(1, idMechanic);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return mechanic;
			}

			mechanic = new MechanicDto();

			mechanic.id = rs.getLong("id");
			mechanic.dni = rs.getString("dni");
			mechanic.name = rs.getString("name");
			mechanic.surname = rs.getString("surname");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return mechanic;
	}

	@Override
	public MechanicDto findInEnrollment(Long idMechanic) throws SQLException {
		MechanicDto mechanic = null;

		PreparedStatement pst = c
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_MECHANIC_ID_IN_ENROLLMENTS"));
		ResultSet rs = null;

		try {

			pst.setLong(1, idMechanic);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return mechanic;
			}

			mechanic = new MechanicDto();

			mechanic.id = rs.getLong("id");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return mechanic;
	}

}
