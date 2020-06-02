package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.WorkOrderGateway;

public class ListCertifiedMechanics {
	private Long id;

	public ListCertifiedMechanics(Long id) {
		this.id = id;
	}

	public List<CertificateDto> execute() throws BusinessException {
		try (Connection c = Jdbc.createThreadConnection();) {
			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			return wog.findCertificatesByVehicleTypeId(id);

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}
}
