package uo.ri.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.CertificateGateway;

public class CertificateGatewayImpl implements CertificateGateway {

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;

	}

	/**
	 * 
	 * Genera certificados de acuerdo con las reglas:
	 * 
	 * - Cada tipo de vehículo especifica la cantidad de horas de capacitación
	 * asistidas y pasadas necesarias para obtener el certificado para ese tipo de
	 * vehículo
	 * 
	 * - El mecánico tiene que acumular al menos esa cantidad de horas en uno o
	 * varios cursos
	 * 
	 * - Un curso especifica el% de horas de capacitación dedicadas a algunos tipos
	 * de vehículos.
	 * 
	 * @return the number of new certificates generated DOES NOT @throws
	 *         BusinessException
	 * @throws SQLException
	 */
	@Override
	public int generateCertificates() throws SQLException {
		int generated = 0;
		PreparedStatement pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_GENERATE_CERTIFICATES"));
		ResultSet rs = null;

		CertificateDto cd = null;

		try {
			rs = pst.executeQuery();
			cd = new CertificateDto();
			cd.mechanic = new MechanicDto();
			cd.vehicleType = new VehicleTypeDto();

			while (rs.next()) {

				cd.mechanic.id = rs.getLong(1);
				cd.vehicleType.id = rs.getLong(2);
				cd.obtainedAt = new Date();

				generarCertificado(cd.obtainedAt, cd.mechanic.id, cd.vehicleType.id);

				generated++;
			}
			return generated;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private void generarCertificado(Date date, Long id_mechanic, Long id_vehicletype) {

		String SQL = Conf.getInstance().getProperty("SQL_INSERT_CERTIFICATES");
		PreparedStatement pst = null;

		try {
			pst = c.prepareStatement(SQL);

			pst.setDate(1, new java.sql.Date(date.getTime()));
			pst.setLong(2, id_mechanic);
			pst.setLong(3, id_vehicletype);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

}
