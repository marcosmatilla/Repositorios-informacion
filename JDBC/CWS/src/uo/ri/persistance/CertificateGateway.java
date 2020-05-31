package uo.ri.persistance;

import java.sql.Connection;
import java.sql.SQLException;

public interface CertificateGateway {
	void setConnection(Connection c);

	int generateCertificates() throws SQLException;
}
