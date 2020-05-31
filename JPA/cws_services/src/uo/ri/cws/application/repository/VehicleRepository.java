package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Vehicle;

public interface VehicleRepository extends Repository<Vehicle> {

	/**
	 * Busca un vehiculo por la matricula
	 * 
	 * @param platepara buscar el vehiculo
	 * @return el vehiculo en caso de que exista o empty en caso de que no
	 */
	Optional<Vehicle> findByPlate(String plate);

}
