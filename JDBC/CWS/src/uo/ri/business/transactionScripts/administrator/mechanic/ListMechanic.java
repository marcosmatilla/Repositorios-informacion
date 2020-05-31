package uo.ri.business.transactionScripts.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.*;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.MechanicGateway;

public class ListMechanic {

	public List<MechanicDto> execute() {
		try (Connection c = Jdbc.createThreadConnection();) {
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);
			return mg.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexción");
		}
	}
}
