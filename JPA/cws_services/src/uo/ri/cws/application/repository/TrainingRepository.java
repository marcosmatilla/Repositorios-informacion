package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.VehicleType;

public interface TrainingRepository extends Repository<Enrollment> {

	List<VehicleType> findTrainingByVehicleTypeId();

	List<Enrollment> findAll();

}
