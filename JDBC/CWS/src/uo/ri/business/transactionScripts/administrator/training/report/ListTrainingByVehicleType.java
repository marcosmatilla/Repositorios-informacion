package uo.ri.business.transactionScripts.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;
import uo.ri.persistance.dedication.DedicationGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;


public class ListTrainingByVehicleType {
	
	public List<TrainingHoursRow> execute() throws BusinessException {
		List<TrainingHoursRow> res = new ArrayList<TrainingHoursRow>();;
		TrainingHoursRow t;

		List<VehicleTypeDto> vehicles = getVehicles();

		List<MechanicDto> mechanics = getMechanics();
		for (VehicleTypeDto v : vehicles) {
			for (MechanicDto m : mechanics) {
				int thoras = 0;
				for (CourseDto c : getCourses(v.id, m.id)) {

					int horas = getHoras(c.id);
					int percentage = getPercentage(c.id, v.id);
					int attendance = getAttendance(c.id, m.id);

					thoras += (horas * percentage * attendance) / 10000;

				}
				t = new TrainingHoursRow();
				t.enrolledHours = thoras;
				t.mechanicFullName = new String(m.name + " " + m.surname);
				t.vehicleTypeName = new String(v.name);

				res.add(t);
			}
		}
		return res;
	}

	private int getPercentage(Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationGateway();
			dg.setConnection(c);
			return dg.getPercentage(id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private int getAttendance(Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			cag.setConnection(c);
			return cag.getAttendance(id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private int getHoras(Long id) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.getHoras(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private List<CourseDto> getCourses(Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.getCourses(id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private List<MechanicDto> getMechanics() {
		try (Connection c = Jdbc.createThreadConnection()) {
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);
			return mg.getMechanics();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private List<VehicleTypeDto> getVehicles() {
		try (Connection c = Jdbc.createThreadConnection()) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			return vtg.getVehicles();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
