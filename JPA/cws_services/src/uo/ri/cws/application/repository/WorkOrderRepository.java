package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.WorkOrder;

public interface WorkOrderRepository extends Repository<WorkOrder> {

	/**
	 * @param idsAveria, lista de los id de avería a recuperar
	 * @return lista con averias cuyo id aparece en idsAveria, o lista vacía si
	 *         no hay ninguna
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);

	/**
	 * @param id from the work order that we want to know status
	 * @return status
	 */
	String findStatusById(String id);

	/**
	 * 
	 * @param plateNumber for search the workOrders
	 * @return list with workOrders with that plateNumber
	 */
	List<WorkOrder> findByPlateNumber(String plateNumber);

	/**
	 * 
	 * @param vehicleTypeId for search certificates
	 * @return list of certificates
	 */
	List<Certificate> findCertificatesByVehicleTypeId(String vehicleTypeId);

	/**
	 * 
	 * @return all unfinished workorders
	 */
	List<WorkOrder> findUnfinishedWorkOrder();

	/**
	 * Find all workOrders, used to know things like id's
	 * 
	 * @return list of WorkOrders
	 */
	List<WorkOrder> findAll();

	/**
	 * Indicates if a work order is in two sites at same time
	 * 
	 * @param workOrder to know condition
	 * @return
	 */
	List<WorkOrder> workOrderAtSameTime(WorkOrderDto workOrder);

}