package uo.ri.business.serviceLayer.mechanic.impl;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.mechanic.MechanicCrudService;
import uo.ri.business.transactionScripts.administrator.mechanic.AddMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.DeleteMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.ListMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.UpdateMechanic;

public class MechanicCrudServiceImpl implements MechanicCrudService {

	@Override
	public void addMechanic(MechanicDto mecanico) throws BusinessException {
		AddMechanic ad = new AddMechanic(mecanico);
		ad.execute();

	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		DeleteMechanic dm = new DeleteMechanic(idMecanico);
		dm.execute();

	}

	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		UpdateMechanic um = new UpdateMechanic(mecanico);
		um.execute();

	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		ListMechanic lm = new ListMechanic();
		return lm.execute();
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
