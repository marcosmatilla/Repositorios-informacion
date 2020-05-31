package uo.ri.business.serviceLayer.training.impl;

import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.training.CertificateService;
import uo.ri.business.transactionScripts.administrator.training.certificate.GenerateCertificates;

public class CertificateServiceImpl implements CertificateService {

	@Override
	public int generateCertificates() throws BusinessException {
		GenerateCertificates gc = new GenerateCertificates();
		return gc.execute();
	}

}
