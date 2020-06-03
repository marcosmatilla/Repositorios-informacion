package uo.ri.persistance.foreman.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class WorkOrderGatewayImpl implements WorkOrderGateway {
	Connection con;

	@Override
	public WorkOrderDto registerNew(WorkOrderDto workorder) throws SQLException {
		WorkOrderDto toReturn = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_WORKORDER");
		PreparedStatement pst = null;
		PreparedStatement pst1= null;

		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			
			pst.setLong(1, workorder.vehicleId);
			pst.setString(2, workorder.description);
			Date date = new Date();
			pst.setDate(3, new java.sql.Date(date.getTime()));
			pst.setString(4, "OPEN");
			
			pst.executeUpdate();
			
			pst1 = con.prepareStatement(Conf.getInstance().getProperty("SQL_GET_LAST_WORKORDER_ID"));
			rs = pst1.executeQuery();
			
			if(rs.next() == false)
				return toReturn; //seguir aqui
			
			//devolver DTO con el id
			toReturn = new WorkOrderDto();
			toReturn.id = rs.getLong(1);
					
			return workorder;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
			Jdbc.close(pst1);
		}
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_DESCRIPTION");
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, dto.description);
			pst.setLong(2, dto.id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void deleteWorkOrder(Long id) throws SQLException {
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_WORKORDER_BY_ID");

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
	public WorkOrderDto findWorkOrderById(Long woId) throws SQLException {

		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_WORKORDER_BY_ID"));
		ResultSet rs = null;

		WorkOrderDto wo = null;

		try {
			pst.setLong(1, woId);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return wo;

			wo = new WorkOrderDto();

			wo.id = rs.getLong("id");
			wo.vehicleId = rs.getLong("vehicle_id");
			wo.description = rs.getString("description");
			wo.date = rs.getDate("date");
			wo.status = rs.getString("status");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return wo;
	}

	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders() throws SQLException {
		List<WorkOrderDto> orders;
		WorkOrderDto order;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_WORKORDERS_OPEN_OR_ASSIGN");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			orders = new ArrayList<WorkOrderDto>();

			while (rs.next()) {
				order = new WorkOrderDto();

				order.vehicleId = rs.getLong(1);
				order.description = rs.getString(2);
				order.date = rs.getDate(3);
				order.status = rs.getString(4);
				order.id = rs.getLong(5);

				orders.add(order);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return orders;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) throws SQLException {
		List<WorkOrderDto> orders;

		PreparedStatement pst = con
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_ALL_WORKORDERS_FOR_VEHICLEID"));
		ResultSet rs = null;

		WorkOrderDto wo = null;

		try {
			pst.setLong(1, id);
			rs = pst.executeQuery();

			orders = new ArrayList<WorkOrderDto>();

			while (rs.next()) {
				wo = new WorkOrderDto();

				wo.id = rs.getLong(1);
				wo.vehicleId = rs.getLong(2);
				wo.description = rs.getString(3);
				wo.date = rs.getDate(4);
				wo.status = rs.getString(5);

				orders.add(wo);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return orders;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws SQLException {
		List<WorkOrderDto> orders;

		PreparedStatement pst = con
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_ALL_WORKORDERS_FOR_PLATENUMBER"));
		ResultSet rs = null;

		WorkOrderDto wo = null;

		try {

			pst.setString(1, plate);
			rs = pst.executeQuery();

			orders = new ArrayList<WorkOrderDto>();

			while (rs.next()) {
				wo = new WorkOrderDto();

				wo.id = rs.getLong(1);
				wo.vehicleId = rs.getLong(2);
				wo.description = rs.getString(3);
				wo.date = rs.getDate(4);
				wo.status = rs.getString(5);

				orders.add(wo);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return orders;
	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(Long id) throws SQLException {
		List<CertificateDto> certificados;

		PreparedStatement pst = con
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_CERTIFICATES_BY_VEHICLETYPEID"));
		ResultSet rs = null;

		CertificateDto cd = null;

		try {

			pst.setLong(1, id);
			rs = pst.executeQuery();

			certificados = new ArrayList<CertificateDto>();

			while (rs.next()) {
				cd = new CertificateDto();

				cd.mechanic = new MechanicDto();
				cd.vehicleType = new VehicleTypeDto();

				cd.mechanic.id = rs.getLong(1);
				cd.mechanic.name = rs.getString(2) + " " + rs.getString(5);
				cd.mechanic.dni = rs.getString(3);
				cd.vehicleType.name = rs.getString(4);

				certificados.add(cd);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return certificados;
	}

	@Override
	public void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws SQLException {

		String SQL = Conf.getInstance().getProperty("SQL_ASSIGN_WORKORDER");

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = con.prepareStatement(SQL);
			pst.setLong(1, mechanicId);
			pst.setLong(2, woId);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void setConnection(Connection c) {
		this.con = c;

	}

	@Override
	public WorkOrderDto findInInteventions(Long idWorkOrder) throws SQLException {

		PreparedStatement pst = con
				.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_WORKORDER_IN_INTERVENTIONS"));
		ResultSet rs = null;

		WorkOrderDto wo = null;

		try {

			pst.setLong(1, idWorkOrder);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return wo;

			wo = new WorkOrderDto();

			wo.id = rs.getLong("id");
			return wo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs,pst);
		}

	}

	@Override
	public WorkOrderDto findVehicleById(Long vehicleId) throws SQLException {
		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_VEHICLE_BY_ID"));
		ResultSet rs = null;

		WorkOrderDto wo = null;

		try {

			pst.setLong(1, vehicleId);
			rs = pst.executeQuery();

			if (rs.next() == false)
				return wo;

			wo = new WorkOrderDto();

			wo.id = rs.getLong("id");
			return wo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public boolean workOrderAtSameTime(WorkOrderDto workOrder) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean aux = false;
		try {
			pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_BY_VEHICLEID_AND_DATE"));
			pst.setLong(1, workOrder.vehicleId);
			Date date = new Date();
			pst.setDate(2, new java.sql.Date(date.getTime()));
			rs = pst.executeQuery();

			if (rs.next()) {
				aux = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return aux;
	}

	@Override
	public String findStatudById(Long id) throws SQLException {

		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_STATUS_BY_ID"));
		ResultSet rs = null;

		try {

			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (!rs.next())
				return "";

			return rs.getString("status");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs,pst);
		}

	}

	@Override
	public WorkOrderDto findById(Long idMechanic) throws SQLException {
		WorkOrderDto workorder = null;

		PreparedStatement pst = con.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_MECHANIC_BY_ID"));
		ResultSet rs = null;

		try {

			pst.setLong(1, idMechanic);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return workorder;
			}

			workorder = new WorkOrderDto();

			workorder.id = rs.getLong("id");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return workorder;
	}

}
