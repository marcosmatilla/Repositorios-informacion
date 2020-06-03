package uo.ri.persistance.administrator.training.certificate;

import java.sql.Connection;
import java.sql.SQLException;

public interface CertificateGateway {
	void setConnection(Connection c);

	int generateCertificates() throws SQLException;
}
