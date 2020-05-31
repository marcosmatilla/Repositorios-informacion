package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Dedication;

public interface DedicationRepository extends Repository<Dedication> {

	/**
	 * Lista todas las dedications
	 * 
	 * @return
	 */
	List<Dedication> findAll();
}
