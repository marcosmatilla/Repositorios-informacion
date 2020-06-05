package uo.ri.persistance.administrator.training.report.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.training.report.CourseReportGateway;

public class CourseReportGatewayImpl implements CourseReportGateway {
	Connection con;

	@Override
	public void setConnection(Connection c) {
		this.con = c;

	}
	
	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws SQLException {
		List<CertificateDto> certificates;
		CertificateDto certificate;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATED_BY_VEHICLE_TYPE");
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();
			
			certificates = new ArrayList<CertificateDto>();
			
			while(rs.next()) {
				certificate = new CertificateDto();
				
				certificate.id = rs.getLong("id");
				certificate.obtainedAt = rs.getDate("date");
				certificate.mechanic = new MechanicDto();
				certificate.mechanic.id = rs.getLong("mechanic_id");
				certificate.vehicleType = new VehicleTypeDto();
				certificate.vehicleType.id = rs.getLong("vehicletype_id");
				
				certificates.add(certificate);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return certificates;
	}

}