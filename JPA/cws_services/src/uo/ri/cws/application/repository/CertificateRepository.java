package uo.ri.cws.application.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public interface CertificateRepository extends Repository<Certificate> {

	/**
	 * Comprobacion para saber si tenemos insertado en la tabla certificados el
	 * que se nos genera al pasarle un mecanico y un vtype
	 * 
	 * @param mechanic    para comprobar
	 * @param vehicleType para comprobar
	 * @return el certificado en caso d q lo haya, empty en caso de q no
	 */
	Optional<Certificate> in(Mechanic mechanic, VehicleType vehicleType);

	/**
	 * Busqueda del total de horas de un mecanico en un tipo de vehiculo para la
	 * certificacion
	 * 
	 * @param v tipo de vehiculo
	 * @param m mecanico
	 * @return optional de bigdecimal(double)
	 */
	Optional<BigDecimal> findTotalHours(VehicleType v, Mechanic m);

	/**
	 * Lista todo los certificados
	 * 
	 * @return
	 */
	List<Certificate> findAll();
}
