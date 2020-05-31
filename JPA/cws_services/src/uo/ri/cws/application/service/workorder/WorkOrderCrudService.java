package uo.ri.cws.application.service.workorder;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Mechanic It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface WorkOrderCrudService {

	/**
	 * Busca una orden de trabajo por el identificador
	 * 
	 * @param woId identificador de la orden de trabajo
	 * @return un optional, con la orden de trabajo en caso de existir
	 * @throws BusinessException si el id de la orden de trabajo no existe
	 */
	Optional<WorkOrderDto> findWorkOrderById(String woId)
			throws BusinessException;

	/**
	 * Busca las ordenes de trabajo por la matricula del vehiculo
	 * 
	 * @param plate la matricula del vehiculo del que buscamos las workorder
	 * @return una lista con las work order asociadas a esa matricula
	 * @throws BusinessException si la matricula que buscamos no existe
	 */
	List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
			throws BusinessException;

	/**
	 * Registra una nueva workOrder
	 * 
	 * @param wo la orden de trabajo que quereoms registrar
	 * @throws BusinessException si la workorder no existe
	 */
	void registerNew(WorkOrderDto wo) throws BusinessException;

	/**
	 * Elimina una orden de trabajo
	 * 
	 * @param woId el identificador de la orden de trabajo que queremos eliminar
	 * @throws BusinessException si el identificador no existe
	 */
	void deleteWorkOrder(String woId) throws BusinessException;

	/**
	 * Actualiza una orden de trabajo
	 * 
	 * @param wo con la work order que queremos actualizar
	 * @throws BusinessException si no existe
	 */
	void updateWorkOrder(WorkOrderDto wo) throws BusinessException;

	/**
	 * Lista todas las work order que tenemos
	 * 
	 * @return una lista con todas las workorder
	 * @throws BusinessException
	 */
	List<WorkOrderDto> findAllWorkOrders() throws BusinessException;
}
