package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.VehicleType;

public interface VehicleTypeRepository extends Repository<VehicleType> {

	/**
	 * Lista todos los vehicle types
	 * 
	 * @return
	 */
	List<VehicleType> findAll();


}
