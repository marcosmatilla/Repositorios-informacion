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
			courses = findCoursesByMechanicIdAndVehicleTypeId(idMechanic, vehicle.id);

			int totalHours = 0;
			int hoursAttended = 0;

			for (Long course : courses) {
				int courseHours = findHoursByCourseId(course);
				int attendance = findAttendanceMechanicIdAndCourseId(idMechanic, course); //ROMPE
				int percentage = findPercentageByVehicleTypeIdAndCourseId(vehicle.id, course);

				totalHours += (double) courseHours * (double) percentage * 0.01;
				hoursAttended += (double) courseHours * (double) percentage * 0.01 * (double) attendance * 0.01;
			}
			TrainingForMechanicRow training = new TrainingForMechanicRow();
			training.vehicleTypeName = vehicle.name;
			training.enrolledHours = totalHours;
			training.attendedHours = hoursAttended;
			res.add(training);
		}
		return res;
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

	private List<Long> findCoursesByMechanicIdAndVehicleTypeId(Long idMechanic, Long idVehicleType) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findCoursesByMechanicIdAndVehicleTypeId(idMechanic, idVehicleType);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private int findAttendanceMechanicIdAndCourseId(Long idMechanic, Long idCourse) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			cag.setConnection(c);
			return cag.findEnrollmentSameMechanicAndCourse1(idMechanic, idCourse);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private int findHoursByCourseId(Long id_curso) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findCourseById(id_curso).hours;
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}

	}

	private int findPercentageByVehicleTypeIdAndCourseId(Long idVehicleType, Long idCourse) {
		try (Connection c = Jdbc.createThreadConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationGateway();
			dg.setConnection(c);
			return dg.findPercentageByVehicleTypeIdAndCourseId(idVehicleType, idCourse);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
