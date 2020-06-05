package uo.ri.persistance.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {
	public void setConnection(Connection c);

	void add(MechanicDto mechanic) throws SQLException;

	void delete(Long idMechanic) throws SQLException;

	void update(MechanicDto mechanic) throws SQLException;

	List<MechanicDto> findAll() throws SQLException;

	MechanicDto findByDni(String dni) throws SQLException;

	MechanicDto findById(Long idMechanic) throws SQLException;

	MechanicDto findInEnrollment(Long idMechanic) throws SQLException;

	List<MechanicDto> getMechanics() throws SQLException;
}
