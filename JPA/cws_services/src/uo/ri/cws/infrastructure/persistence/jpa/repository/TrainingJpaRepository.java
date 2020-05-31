package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.TrainingRepository;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class TrainingJpaRepository extends BaseJpaRepository<Enrollment>
		implements TrainingRepository {

	/**
	 * Metodo que nos busca el training por el id del vehiculo
	 */
	@Override
	public List<VehicleType> findTrainingByVehicleTypeId() {
		return Jpa.getManager()
				.createNamedQuery("Enrollment.findAllVehicleTypes",
						VehicleType.class)
				.getResultList();
	}

}
