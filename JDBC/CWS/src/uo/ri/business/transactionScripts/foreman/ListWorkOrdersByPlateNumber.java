package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.vehicle.VehicleGateway;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class ListWorkOrdersByPlateNumber {
	private String plate;

	public ListWorkOrdersByPlateNumber(String plate) {
		this.plate = plate;
	}

	public List<WorkOrderDto> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			VehicleGateway vg = PersistenceFactory.getVehicleGateway();
			wog.setConnection(c);
			vg.setConnection(c);
			if(vg.findVehicleByPlate(plate) == null) {
				c.rollback();
				throw new BusinessException("plate does not exist");
			}
			return wog.findWorkOrdersByPlateNumber(plate);

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
