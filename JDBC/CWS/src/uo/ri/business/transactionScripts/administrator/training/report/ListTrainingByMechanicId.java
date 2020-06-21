package uo.ri.business.transactionScripts.administrator.training.report;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;
import uo.ri.persistance.dedication.DedicationGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class ListTrainingByMechanicId {
	private Long idMechanic;

	public ListTrainingByMechanicId(Long idMechanic) {
		this.idMechanic = idMechanic;
	}

	public List<TrainingForMechanicRow> execute() {
		List<TrainingForMechanicRow> res = new ArrayList<TrainingForMechanicRow>();
		List<VehicleTypeDto> vehicleTypes = findVehicleTypeByMechanicId();
		List<Long> courses;

		for (VehicleTypeDto vehicle : vehicleTypes) {
			courses = getCourses(vehicle.id, idMechanic);

			int totalHours = 0;
			int hoursAttended = 0;

			for (Long course : courses) {
				int courseHours = getHoras(course);
				int attendance = getAttendance(course, idMechanic);
				int percentage = getPercentage(course, vehicle.id);

				totalHours += (double) courseHours * (double) percentage * 0.01;
				hoursAttended += (int) (courseHours * attendance * percentage) / 10000;
			}

			TrainingForMechanicRow training = new TrainingForMechanicRow();

			training.vehicleTypeName = vehicle.name;
			training.enrolledHours = totalHours;
			training.attendedHours = hoursAttended;

			res.add(training);
		}
		return res;
	}

	private List<Long> getCourses(Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findCoursesByMechanicIdAndVehicleTypeId(id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
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

	private List<VehicleTypeDto> findVehicleTypeByMechanicId() {
		try (Connection c = Jdbc.createThreadConnection()) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			return vtg.findVehicleTypeByMechanicId(idMechanic);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
