package uo.ri.ui.administrator.training.certificate;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.administrator.training.certificate.CertificateService;
import uo.ri.conf.ServiceFactory;

public class GenerateCertificatesAction implements Action {

	@Override
	public void execute() throws Exception {

		Console.println("Generating certificates...");

		CertificateService cs = ServiceFactory.getCertificateService();
		int qty = cs.generateCertificates();

		Console.println(qty + " certificates generated");
	}

}
