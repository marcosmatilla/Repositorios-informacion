package uo.ri.cws.application.service.workorder;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateDto;

public interface AssignWorkOrderService {

	/**
	 * Asigna una averia a un mecanico
	 * 
	 * @param woId de la averia a asignar
	 * @param mId  del mecanico a asignar
	 * @throws BusinessException si no existe alguno de los dos
	 */
	void assignWorkOrderToMechanic(String woId, String mId) throws BusinessException;

	/**
	 * Busca y lista los certificados por tipo de vehiculo
	 * 
	 * @param vtId del tipo de vehiculo
	 * @return lista con los certificados por tipo de vehiculo
	 * @throws BusinessException si no existe el id del vehiculo
	 */
	List<CertificateDto> findCertificatesByVehicleTypeId(String vtId) throws BusinessException;

	/**
	 * Lista las ordenes de trabajo que no estan terminadas y las muestra
	 * 
	 * @return ordenes de trabajo no terminadas (OPEN O ASSIGN)
	 * @throws BusinessException
	 */
	List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException;

}
