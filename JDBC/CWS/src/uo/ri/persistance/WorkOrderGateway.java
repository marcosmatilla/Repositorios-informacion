package uo.ri.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;

public interface WorkOrderGateway {

	void setConnection(Connection c);

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
	WorkOrderDto registerNew(WorkOrderDto dto) throws SQLException;

	/**
	 * Updates the description of the work order specified by the id and version
	 * fields. No other field is taken for the update. Only work orders with status
	 * of OPEN or ASSIGNED can be updated.
	 *
	 * @param dto, with work order id, version and description.
	 *
	 * @throws BusinessException if: - there is no work order with that id, or -
	 *                           there work order has not the specified version
	 *                           (optimistic lock), or - the work order is not in
	 *                           the OPEN or ASSIGNED status
	 */
	void updateWorkOrder(WorkOrderDto dto) throws SQLException;

	/**
	 * Removes the work order form the system if it still do not have interventions.
	 *
	 * @param id, of the work order
	 *
	 * @throws BusinessException if: - the work order does not exist, or - there
	 *                           already is some intervention registered.
	 */
	void deleteWorkOrder(Long id) throws SQLException;

	/**
	 * @param woId, id of the work order
	 * @return the optional filled if the work order exists
	 * @throws BusinessException
	 */
	WorkOrderDto findWorkOrderById(Long woId) throws SQLException;

	/**
	 * @return a list of all work orders either in the OPEN or ASSIGN status
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findUnfinishedWorkOrders() throws SQLException;

	/**
	 * Returns a list of work orders registered for the specified vehicle id
	 * 
	 * @param id
	 * @return the list, will be empty if: - the vehicle does not exist, or - it has
	 *         no work orders registered.
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findWorkOrdersByVehicleId(Long id) throws SQLException;

	/**
	 * Returns a list of work orders registered for the specified plate number
	 * 
	 * @param plate
	 * @return the list, will be empty if: - the vehicle does not exist, or - it has
	 *         no work orders registered.
	 * @throws BusinessException, DOES NOT
	 */
	List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws SQLException;

	/**
	 * Returns a list of certificates (i.e, certified mechanics) for the vehicle
	 * type. Every certificate includes full mechanic data (@see MechanicDto).
	 *
	 * @param id of the vehicle type
	 *
	 * @return the list. It might be empty if no mechanic is certified for the
	 *         specified vehicle type.
	 * @throws BusinessException
	 */
	List<CertificateDto> findCertificatesByVehicleTypeId(Long id) throws SQLException;

	/**
	 * Assigns the work order to mechanic so the he/she can see what work has to
	 * today/next. Only work orders with OPEN status can be assigned. Work orders
	 * changes to ASSIGNED status when assigned.
	 *
	 * @param woId, the work order id
	 * @param mechanicId, the mechanic one
	 *
	 * @throws BusinessException if: - the mechanic does not exist, or - the work
	 *                           order does not exist, or - the work order is not in
	 *                           OPEN status
	 */
	void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws SQLException;

	/**
	 * Find if a workOrder is in an intervention
	 * 
	 * @param idWorkOrder, type long
	 * @return WorkOrderDto
	 * @throws SQLException
	 */
	WorkOrderDto findInInteventions(Long idWorkOrder) throws SQLException;

	/**
	 * Find a vehicle by id
	 * 
	 * @param vehicleId, type Long
	 * @return WorkOrderDto
	 * @throws SQLException
	 */
	WorkOrderDto findVehicleById(Long vehicleId) throws SQLException;

	/**
	 * Indicates if a work order is in two sites at same time
	 * 
	 * @param WokOrderDto
	 * @return boolean
	 * @throws SQLException
	 */
	boolean workOrderAtSameTime(WorkOrderDto workOder) throws SQLException;

	/**
	 * find for the status of a work order
	 * 
	 * @param id type Long
	 * @return String
	 * @throws SQLException
	 */
	String findStatudById(Long id) throws SQLException;

	/**
	 * find for a mechanic
	 * 
	 * @param idMechanic
	 * @return WorkOrderDto
	 * @throws SQLException
	 */
	WorkOrderDto findById(Long idMechanic) throws SQLException;
}
