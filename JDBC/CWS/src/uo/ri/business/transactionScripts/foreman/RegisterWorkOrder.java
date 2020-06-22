package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.vehicle.VehicleGateway;
import uo.ri.persistance.foreman.WorkOrderGateway;

public class RegisterWorkOrder {
	private WorkOrderDto workOrder;

	public RegisterWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}

	/**
	 * Registers a new work order out of the data received. Only this fields will be
	 * taken into account: - the vehicle id, and - the description of the work to be
	 * done The rest of fields will assigned by the service, thus any provided value
	 * will be ignored.
	 *
	 * @param dto. Just vehicle id and description.
	 *
	 * @return another dto with the provided values and service-assigned fields
	 *         filled: id, date and status
	 *
	 * @throws BusinessException if: - there is another work order for the same
	 *                           vehicle at the same date and time (timestamp), or -
	 *                           the vehicle does not exist
	 */
	public WorkOrderDto execute() throws BusinessException {

		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			VehicleGateway vg = PersistenceFactory.getVehicleGateway();
			wog.setConnection(c);
			vg.setConnection(c);
			if (vg.findVehicleById(workOrder.vehicleId) == null) {
				c.rollback();
				throw new BusinessException("vehicle does not exist");
			}
			if (wog.workOrderAtSameTime(workOrder)) {
				c.rollback();
				throw new BusinessException("this vehicle has another work order");
			}
			wog.registerNew(workOrder);
			c.commit();

			/*WorkOrderDto forId = wog.findWorkOrderForId(workOrder.vehicleId, workOrder.description, workOrder.status);
			workOrder.id = forId.id;*/

			return workOrder;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}
}
