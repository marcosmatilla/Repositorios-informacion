package uo.ri.persistance.administrator.training.course.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.administrator.training.course.CourseGateway;

public class CourseGatewayImpl implements CourseGateway {
	Connection con;

	@Override
	public void setConnection(Connection con) {
		this.con = con;

	}

	@Override
	public void registerNew(CourseDto course) {
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_COURSE");
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(SQL);

			pst.setString(1, course.code);
			pst.setString(2, course.description);
			pst.setDate(3, new java.sql.Date(course.endDate.getTime()));
			pst.setInt(4, course.hours);
			pst.setString(5, course.name);
			pst.setDate(6, new java.sql.Date(course.startDate.getTime()));

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void updateCourse(CourseDto course) {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_COURSE");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);

			pst.setString(1, course.code);
			pst.setString(2, course.description);
			pst.setDate(3, (Date) course.endDate);
			pst.setInt(4, course.hours);
			pst.setString(5, course.name);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void deleteCourse(Long id) {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_COURSE_BY_ID");

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public List<CourseDto> findAllCourses() {
		List<CourseDto> courses;
		CourseDto course;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_ACTIVE_COURSES");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			courses = new ArrayList<CourseDto>();

			while (rs.next()) {
				course = new CourseDto();

				course.code = rs.getString("code");
				course.name = rs.getString("name");
				course.description = rs.getString("description");
				course.startDate = rs.getDate("startdate");
				course.endDate = rs.getDate("enddate");
				course.hours = rs.getInt("hours");

				courses.add(course);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return courses;
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws SQLException {
		List<VehicleTypeDto> vehicleTypes;
		VehicleTypeDto vt = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLE_TYPES");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			vehicleTypes = new ArrayList<VehicleTypeDto>();

			while (rs.next()) {
				vt = new VehicleTypeDto();

				vt.name = rs.getString("name");
				vt.pricePerHour = rs.getDouble("priceperhour");
				vt.minTrainigHours = rs.getInt("minTrainingHours");

				vehicleTypes.add(vt);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return vehicleTypes;
	}

	@Override
	public CourseDto findCourseById(Long cId) throws SQLException {

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_ID");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		CourseDto co = null;

		try {
			pst.setLong(1, cId);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return co;
			}

			co = new CourseDto();

			co.code = rs.getString("code");
			co.name = rs.getString("name");
			co.description = rs.getString("description");
			co.startDate = rs.getDate("startdate");
			co.endDate = rs.getDate("enddate");
			co.hours = rs.getInt("hours");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return co;
	}

	@Override
	public CourseDto findCourseByName(String name) throws SQLException {
		CourseDto course = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_NAME");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		try {
			pst.setString(1, name);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return course;

			course = new CourseDto();

			course.id = rs.getLong("id");
			course.code = rs.getString("code");
			course.name = rs.getString("name");
			course.description = rs.getString("description");
			course.startDate = rs.getDate("startdate");
			course.endDate = rs.getDate("enddate");
			course.hours = rs.getInt("hours");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return course;
	}

	@Override
	public CourseDto findInEnrollment(Long idCourse) throws SQLException {
		CourseDto course = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_IN_ENROLLMENT");
		PreparedStatement pst = con.prepareStatement(SQL);
		ResultSet rs = null;

		try {
			pst.setLong(1, idCourse);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return course;
			}

			course = new CourseDto();
			course.id = rs.getLong("id");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return course;
	}

	@Override
	public List<Long> findCoursesByMechanicIdAndVehicleTypeId(Long idMechanic, Long idCourse) throws SQLException {
		List<Long> courses = null;
		CourseDto course = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_MECHANICID_AND_VEHICLETYPEID");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			pst.setLong(2, idCourse);
			rs = pst.executeQuery();

			courses = new ArrayList<Long>();

			while (rs.next()) {
				courses.add(rs.getLong("course_id"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return courses;
	}

}
