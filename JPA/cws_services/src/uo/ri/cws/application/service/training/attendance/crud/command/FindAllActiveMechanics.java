package uo.ri.cws.application.service.training.attendance.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class FindAllActiveMechanics implements Command<List<MechanicDto>> {
	private EnrollmentRepository repoC = Factory.repository.forEnrollment();

	public List<MechanicDto> execute() throws BusinessException {
		List<MechanicDto> res = new ArrayList<>();

		List<Mechanic> mechanics = repoC.findAllActiveMechanics();
		for (Mechanic m : mechanics) {
			MechanicDto mechanic = new MechanicDto();
			mechanic.dni = m.getDni();
			mechanic.name = m.getName();
			mechanic.surname = m.getSurname();
			mechanic.id = m.getId();
			res.add(mechanic);
		}
		return res;
	}
}
