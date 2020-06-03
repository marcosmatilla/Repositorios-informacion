package uo.ri.business.serviceLayer.administrator.vehicle.impl;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.vehicle.VehicleCrudService;
import uo.ri.business.transactionScripts.administrator.vehicle.FindVehicleById;
import uo.ri.business.transactionScripts.administrator.vehicle.FindVehicleByPlate;

public class VehicleCrudServiceImpl implements VehicleCrudService {

	@Override
	public VehicleDto findVehicleByPlate(String plate) throws BusinessException {
		FindVehicleByPlate fvbp = new FindVehicleByPlate(plate);
		return fvbp.execute();
	}

	@Override
	public VehicleDto findVehicleById(Long id) throws BusinessException {
		FindVehicleById fvbi = new FindVehicleById(id);
		return fvbi.execute();
	}

}
