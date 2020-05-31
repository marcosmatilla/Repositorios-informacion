package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder>
		implements WorkOrderRepository {

	/**
	 * Metodo que nos busca y lista todas las work order
	 */
	@Override
	public List<WorkOrder> findByIds(List<String> idsAveria) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByIds", WorkOrder.class)
				.setParameter(1, idsAveria).getResultList();
	}

	/**
	 * Metodo que nos busca el estado de una workorder dependiendo del id que le
	 * pasamos como parametro
	 */
	@Override
	public String findStatusById(String id) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findStatusById", String.class)
				.setParameter(1, id).getSingleResult();
	}

	/**
	 * metodo que nos busca una work order por la matricula del vehiculo que le
	 * pasamos como parametro
	 */
	@Override
	public List<WorkOrder> findByPlateNumber(String plateNumber) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findByPlateNumber",
						WorkOrder.class)
				.setParameter(1, plateNumber).getResultList();
	}

	/**
	 * Metodo que nos devuelve los certificados por tipo de vehiculo pasado como
	 * paremtro
	 */
	@Override
	public List<Certificate> findCertificatesByVehicleTypeId(
			String vehicleTypeId) {
		return Jpa.getManager()
				.createNamedQuery(
						"WorkOrder.findCertifiedMechanicsByVehicleTypeId",
						Certificate.class)
				.setParameter(1, vehicleTypeId).getResultList();
	}

	/**
	 * Metodo que nos devuelve las work order que no estan terminadas
	 */
	@Override
	public List<WorkOrder> findUnfinishedWorkOrder() {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findUnfinishedWorkOrders",
						WorkOrder.class)
				.getResultList();
	}

	/**
	 * Metodo que nos devuelve si existe una workorder en mismo tiempo en dos
	 * lugares a la vez
	 */
	@Override
	public List<WorkOrder> workOrderAtSameTime(WorkOrderDto workOrder) {
		return Jpa.getManager()
				.createNamedQuery("WorkOrder.findWorkOrderAtSameTime",
						WorkOrder.class)
				.setParameter(1, workOrder.vehicleId)
				.setParameter(2, workOrder.date).getResultList();
	}

}
