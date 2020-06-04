package uo.ri.persistance.administrator.training.report.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.training.report.CourseReportGateway;

public class CourseReportGatewayImpl implements CourseReportGateway {
	Connection con;

	@Override
	public void setConnection(Connection c) {
		this.con = c;

	}

	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws SQLException {

		List<TrainingHoursRow> res = null;
		TrainingHoursRow t;

		List<VehicleTypeDto> vehicles = getVehicles();

		List<MechanicDto> mechanics = getMechanics();

		PreparedStatement pst2 = null;
		ResultSet rs2 = null;

		PreparedStatement pst3 = null;
		ResultSet rs3 = null;

		PreparedStatement pst4 = null;
		ResultSet rs4 = null;

		try {

			res = new ArrayList<TrainingHoursRow>();

			for (VehicleTypeDto v : vehicles) {
				for (MechanicDto m : mechanics) {
					int thoras = 0;
					for (CourseDto c : getCourses(v.id, m.id)) {

						int horas = getHoras(c.id);
						int percentage = getPercentage(c.id, v.id);
						int attendance = getAttendance(c.id, m.id);

						thoras += (horas * percentage * attendance) / 10000;

					}
					t = new TrainingHoursRow();
					t.enrolledHours = thoras;
					t.mechanicFullName = new String(m.name + " " + m.surname);
					t.vehicleTypeName = new String(v.name);

					res.add(t);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Jdbc.close(rs2, pst2);
		Jdbc.close(rs3, pst3);
		Jdbc.close(rs4, pst4);
		return res;
	}

	private int getAttendance(Long idC, Long idM) throws SQLException {
		int attendance = 1;
		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_ATTENDANCE_BY_ID"));
		ResultSet rs = null;

		try {
			pst.setLong(1, idC);
			pst.setLong(2, idM);

			rs = pst.executeQuery();

			if (!rs.next()) {
				return attendance;
			}

			attendance = rs.getInt(1);
			return attendance;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private int getPercentage(Long idC, Long idV) throws SQLException {
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

	private int getHoras(Long idC) throws SQLException {
		int horas = 1;
		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_HOURS_BY_ID"));
		ResultSet rs = null;

		try {
			pst.setLong(1, idC);
			rs = pst.executeQuery();

			if (!rs.next()) {
				return horas;
			}

			horas = rs.getInt(1);
			return horas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private List<CourseDto> getCourses(Long idV, Long idM) throws SQLException {
		List<CourseDto> res;
		CourseDto course = null;

		PreparedStatement pst = con
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_COURSE_VEHICLETYPEID_MECHANICID"));
		ResultSet rs = null;

		try {
			res = new ArrayList<CourseDto>();
			pst.setLong(1, idV);
			pst.setLong(2, idM);

			rs = pst.executeQuery();

			while (rs.next()) {
				course = new CourseDto();
				course.id = rs.getLong(1);

				res.add(course);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return res;
	}

	private List<VehicleTypeDto> getVehicles() throws SQLException {
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

	private List<MechanicDto> getMechanics() throws SQLException {
		List<MechanicDto> res;
		MechanicDto mechanic = null;

		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_ACTIVE_MECHANICS"));
		ResultSet rs = null;

		try {
			res = new ArrayList<MechanicDto>();

			rs = pst.executeQuery();

			while (rs.next()) {
				mechanic = new MechanicDto();

				mechanic.id = rs.getLong(1);
				mechanic.dni = rs.getString(2);
				mechanic.name = rs.getString(3);
				mechanic.surname = rs.getString(4);

				res.add(mechanic);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return res;
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