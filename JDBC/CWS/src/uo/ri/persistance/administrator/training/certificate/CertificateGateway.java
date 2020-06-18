package uo.ri.persistance.administrator.training.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public interface CertificateGateway {
	void setConnection(Connection c);

	int generateCertificates() throws SQLException;
	
	boolean isMechanicCertificateForVehicleType(Long idMechanic, Long idVehicletype) throws SQLException;
	
	void generarCertificado(Date date, Long id_mechanic, Long id_vehicletype) throws SQLException;
}
