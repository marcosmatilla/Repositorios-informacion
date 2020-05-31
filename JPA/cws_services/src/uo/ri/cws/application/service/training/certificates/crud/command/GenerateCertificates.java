package uo.ri.cws.application.service.training.certificates.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class GenerateCertificates implements Command<Integer> {

	private CertificateRepository repoC = Factory.repository.forCerticiate();
	private MechanicRepository repoM = Factory.repository.forMechanic();
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();

	@Override
	public Integer execute() throws BusinessException {
		int cont = 0;
		int totalHoras = 0;

		List<Mechanic> mechanics = repoM.findAll();
		List<VehicleType> vTypes = repoV.findAll();

		for (VehicleType v : vTypes) {
			for (Mechanic m : mechanics) {
				if (!repoC.in(m, v).isPresent()) {
					totalHoras = repoC.findTotalHours(v, m).get().intValue();
					if (totalHoras >= v.getMinTrainingHours()) {
						repoC.add(new Certificate(m, v));
						cont++;
					}
				}
			}
		}

		return cont;
	}
}
