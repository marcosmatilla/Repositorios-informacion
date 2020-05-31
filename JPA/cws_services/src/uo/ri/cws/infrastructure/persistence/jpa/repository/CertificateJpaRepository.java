package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.math.BigDecimal;
import java.util.Optional;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class CertificateJpaRepository extends BaseJpaRepository<Certificate>
		implements CertificateRepository {

	/**
	 * Metodo para saber si los certificados ya estan insertados en la tabla
	 */
	@Override
	public Optional<Certificate> in(Mechanic mechanic,
			VehicleType vehicleType) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.FindCertifiedMechanic",
						Certificate.class)
				.setParameter(1, mechanic).setParameter(2, vehicleType)
				.getResultStream().findFirst();
	}

	/**
	 * Metodo para buscar las horas totales para los certificados
	 */
	@Override
	public Optional<BigDecimal> findTotalHours(VehicleType v, Mechanic m) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.FindTotalHours",
						BigDecimal.class)
				.setParameter(1, v).setParameter(2, m).getResultStream()
				.findFirst();
	}

}
