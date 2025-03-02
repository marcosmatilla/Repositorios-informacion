package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class MechanicJpaRepository extends BaseJpaRepository<Mechanic>
		implements MechanicRepository {

	/**
	 * Metodo que nos busca un mecanico por su dni
	 */
	@Override
	public Optional<Mechanic> findByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("Mechanic.findByDni", Mechanic.class)
				.setParameter(1, dni).getResultStream().findFirst();
	}

	@Override
	public List<Mechanic> findMechanicInEnrolls() {
		return Jpa
				.getManager()
				.createNamedQuery("Mechanic.findMechanicInEnrolls",
						Mechanic.class)
				.getResultList();
	}
	
	

}
