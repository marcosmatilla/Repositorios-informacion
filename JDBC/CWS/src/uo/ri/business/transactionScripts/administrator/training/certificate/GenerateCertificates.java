package uo.ri.business.transactionScripts.administrator.training.certificate;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.certificate.CertificateGateway;

public class GenerateCertificates {

	/**
	 * Generates certificates according to the rules: - Each vehicle type specifies
	 * the number of attended-and-passed training hours needed to get the
	 * certificate for that vehicle type
	 * 
	 * - The mechanic has to accumulate at least that number of hours in one or
	 * several courses
	 * 
	 * - A course specifies the % of training hours devoted to some vehicle types
	 * 
	 * @return the number of new certificates generated DOES NOT @throws
	 *         BusinessException
	 */
	public int execute() throws BusinessException {
		int n;
		try (Connection c = Jdbc.createThreadConnection()) {
			CertificateGateway cg = PersistenceFactory.getCertificateGateway();
			c.setAutoCommit(false);
			cg.setConnection(c);
			n = cg.generateCertificates();
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		return n;
	}
}
