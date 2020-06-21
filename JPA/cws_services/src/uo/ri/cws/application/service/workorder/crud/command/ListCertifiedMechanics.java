package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.VehicleType;

public class ListCertifiedMechanics implements Command<List<CertificateDto>> {
	private String vehicleTypeId;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();

	public ListCertifiedMechanics(String vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	@Override
	public List<CertificateDto> execute() throws BusinessException {
		BusinessCheck.isNotEmpty(this.vehicleTypeId, "The plate cannot be empty");
		BusinessCheck.isNotNull(this.vehicleTypeId, "The plate cannot be null");
		Optional<VehicleType> v = repoV.findById(vehicleTypeId);
		BusinessCheck.exists(v, "Vehicle type does not exist");

		List<Certificate> c = repo.findCertificatesByVehicleTypeId(vehicleTypeId);
		return DtoAssembler.toCertificateDtoList(c);
	}

}
