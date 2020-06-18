package uo.ri.persistance.dedication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.DedicationDto;

public interface DedicationGateway {
	void setConnection(Connection con);

	int findPercentageByVehicleTypeIdAndCourseId(Long idVehicletype, Long idCourse) throws SQLException;
	
	int getPercentage(Long idC, Long idV) throws SQLException;

	void deleteCourseFromDedication(Long idCourse);

	void addDedicacion(Long i, Integer integer, Long id);
	
	List<DedicationDto> findAll() throws SQLException;

	List<DedicationDto> findWhereCourseId(Long idCourse) throws SQLException;
	
	DedicationDto getDedication(Long idC) throws SQLException;
}
