package uo.ri.business.serviceLayer.vehicle;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;

/**
 * This service is intended to be used by the Foreman It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface VehicleCrudService {

	/**
	 * method to find a vehicle by the plate
	 * 
	 * @param plate number, type String
	 * @return a vehicle dto specified be the plate number
	 * 
	 * @throws BusinessException, DOES NOT
	 */
	VehicleDto findVehicleByPlate(String plate) throws BusinessException;

	/**
	 * method to find the vehicle by th id
	 * 
	 * @param id, type Long
	 * @return a VehicleDto specified by the id
	 * @throws BusinessException
	 */
	VehicleDto findVehicleById(Long id) throws BusinessException;

}
