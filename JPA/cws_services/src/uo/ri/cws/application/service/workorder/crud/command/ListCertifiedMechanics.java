package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;

public class ListCertifiedMechanics implements Command<List<CertificateDto>> {
	private String vehicleTypeId;
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();

	public ListCertifiedMechanics(String vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	@Override
	public List<CertificateDto> execute() throws BusinessException {
		List<Certificate> c = repo
				.findCertificatesByVehicleTypeId(vehicleTypeId);

		return DtoAssembler.toCertificateDtoList(c);
	}

}
