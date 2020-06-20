package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class EnrollmentJpaRepository extends BaseJpaRepository<Enrollment>
		implements EnrollmentRepository {

	/**
	 * Metodo que devuelve una lista con todos los mecanicos activos
	 */
	@Override
	public List<Mechanic> findAllActiveMechanics() {
		return Jpa.getManager()
				.createNamedQuery("AttendanceCourse.FindAllActiveMechanics",
						Mechanic.class)
				.getResultList();
	}

	/**
	 * Metodo que devuelve una lista con todos los cursos activos
	 */
	@Override
	public List<Course> findAllActiveCourses() {
		return Jpa.getManager()
				.createNamedQuery("Enrollment.findActiveCourses", Course.class)
				.getResultList();
	}

	/**
	 * Metodo que nos busca la attendance del curso dependiendo del id del curso
	 * que le pasemos como parametro
	 */
	@Override
	public List<Enrollment> findAttendanceInCourse(String id) {
		return Jpa.getManager()
				.createNamedQuery("Enrollment.FindAttendanceInCourse",
						Enrollment.class)
				.setParameter(1, id).getResultList();
	}

	/**
	 * Metodo que nos busca si ya existe un mecanico asignado a un curso
	 */
	@Override
	public Optional<Enrollment> findEnrollForCourseAndMechanic(String courseId,
			String mechanicId) {
		return Jpa.getManager()
				.createNamedQuery("Enrollment.FindEnrollForCourseAndMechanic",
						Enrollment.class)
				.setParameter(1, courseId).setParameter(2, mechanicId)
				.getResultStream().findFirst();
	}
	
	@Override
	public List<Enrollment> findTrainingByMechanicId(String id) {

		List<Enrollment> i = Jpa.getManager()
				.createNamedQuery("Enrollment.findEnrForMech", Enrollment.class)
				.setParameter(1, id).getResultList();

		return i;
	}

}
