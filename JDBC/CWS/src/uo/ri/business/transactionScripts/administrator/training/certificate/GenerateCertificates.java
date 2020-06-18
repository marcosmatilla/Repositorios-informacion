package uo.ri.business.transactionScripts.administrator.training.certificate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.DedicationDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;
import uo.ri.persistance.administrator.training.certificate.CertificateGateway;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;
import uo.ri.persistance.dedication.DedicationGateway;
import uo.ri.persistance.vehicletype.VehicleTypeGateway;

public class GenerateCertificates {

	/**
	 * Generates certificates according to the rules: - Each vehicle type specifies
	 * the number of attended-and-passed training hours needed to get the
	 * certificate for that vehicle type
	 * 
	 * - The mechanic has to accumulate at least that number of hours in one or
	 * several courses
	 * 
	 * - A course specifies the % of training hours devoted to some vehicle types
	 * 
	 * @return the number of new certificates generated DOES NOT @throws
	 *         BusinessException
	 */
	public int execute() throws BusinessException {
		int n = 0;

		List<MechanicDto> mechanics = getMechanics();
		List<VehicleTypeDto> vehicleTypes = getVehicleTypes();
		List<EnrollmentDto> enrollments = new ArrayList<>();
		for (MechanicDto mechanic : mechanics) {
			for (VehicleTypeDto vehicleType : vehicleTypes) {
				enrollments = mechanicInEnroll(mechanic.id);

				for (EnrollmentDto enrollment : enrollments) {
					CourseDto course = getCourse(enrollment.courseId);
					List<DedicationDto> dedications = getDedications(course.id);

					int percentage = getPercentage(enrollment.courseId, vehicleType.id);

					for (DedicationDto dedication : dedications) {
						if (!isCertificate(mechanic.id, vehicleType.id)
								&& vehicleType.id == dedication.vehicleType_id) {

							if (((enrollment.attendance * course.hours * percentage)
									/ 10000) >= vehicleType.minTrainigHours && enrollment.passed == true) {
								generateCertificate(new Date(), mechanic.id, vehicleType.id);

								n += 1;
							}

						}
					}

				}

			}
		}
		return n;
	}

	private void generateCertificate(Date date, Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CertificateGateway cg = PersistenceFactory.getCertificateGateway();
			cg.setConnection(c);
			cg.generarCertificado(date, id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}

	}

	private List<DedicationDto> getDedications(Long idCourse) {
		try (Connection c = Jdbc.createThreadConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationGateway();
			dg.setConnection(c);
			return dg.findWhereCourseId(idCourse);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private List<EnrollmentDto> mechanicInEnroll(Long idMechanic) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseAttendanceGateway cg = PersistenceFactory.getCourseAttendanceGateway();
			cg.setConnection(c);
			return cg.mechanicInEnroll(idMechanic);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private CourseDto getCourse(Long id) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findCourseById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private boolean isCertificate(Long idMechanic, Long idVehicletype) {
		try (Connection c = Jdbc.createThreadConnection()) {
			CertificateGateway cg = PersistenceFactory.getCertificateGateway();
			cg.setConnection(c);
			return cg.isMechanicCertificateForVehicleType(idMechanic, idVehicletype);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

	private List<VehicleTypeDto> getVehicleTypes() {
		try (Connection c = Jdbc.createThreadConnection()) {
			VehicleTypeGateway vtg = PersistenceFactory.getVehicleTypeGateway();
			vtg.setConnection(c);
			return vtg.getVehicles();
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

	private int getPercentage(Long id, Long id2) {
		try (Connection c = Jdbc.createThreadConnection()) {
			DedicationGateway dg = PersistenceFactory.getDedicationGateway();
			dg.setConnection(c);
			return dg.getPercentage(id, id2);
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	}

}
