package uo.ri.cws.application.service.training.certificates.crud;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateService;
import uo.ri.cws.application.service.training.certificates.crud.command.GenerateCertificates;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CertificateServiceImpl implements CertificateService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public int generateCertificates() throws BusinessException {
		return executor.execute(new GenerateCertificates());
	}

}
