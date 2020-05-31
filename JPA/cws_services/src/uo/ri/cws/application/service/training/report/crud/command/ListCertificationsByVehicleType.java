package uo.ri.cws.application.service.training.report.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.certificates.CertificateDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.VehicleType;

public class ListCertificationsByVehicleType
		implements Command<List<CertificateDto>> {
	private CertificateRepository repo = Factory.repository.forCerticiate();
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();

	@Override
	public List<CertificateDto> execute() throws BusinessException {
		List<CertificateDto> res = new ArrayList<>();

		List<VehicleType> vehicleTypes = repoV.findAll();
		List<Certificate> certificates = repo.findAll();

		for (VehicleType v : vehicleTypes) {
			for (Certificate c : certificates) {
				if (c.getVehicleType().equals(v)) {
					CertificateDto cDto = new CertificateDto();
					cDto.mechanic = new MechanicDto();
					cDto.vehicleType = new VehicleTypeDto();
					cDto.mechanic.name = c.getMechanic().getName();
					cDto.mechanic.surname = c.getMechanic().getSurname();
					cDto.vehicleType.id = c.getVehicleType().getId();
					cDto.vehicleType.name = c.getVehicleType().getName();
					cDto.obtainedAt = c.getDate();
					res.add(cDto);
				}
			}
		}

		return res;
	}

}
