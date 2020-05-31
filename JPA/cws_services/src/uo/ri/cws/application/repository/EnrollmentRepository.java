package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public interface EnrollmentRepository extends Repository<Enrollment> {

	/**
	 * Lista todos los mecanicos activos
	 * 
	 * @return lista mecanicos
	 */
	List<Mechanic> findAllActiveMechanics();

	/**
	 * Lista todos los cursos activos
	 * 
	 * @return lista cursos
	 */
	List<Course> findAllActiveCourses();

	/**
	 * Lista todos los enrollments
	 * 
	 * @return
	 */
	List<Enrollment> findAll();

	/**
	 * Lista la attendance en el curso que pasamos como paramtro
	 * 
	 * @param id del curso del cual queremos saber la attendance
	 * @return lista
	 */
	List<Enrollment> findAttendanceInCourse(String id);

	/**
	 * Busca si existe un enroll para un mecanico en un curso
	 * 
	 * @param course   que queremos buscar
	 * @param mechanic que queremos ver si esta enroll en el curso
	 * @return empty si no hay nada
	 */
	Optional<Enrollment> findEnrollForCourseAndMechanic(String courseId,
			String mechanicId);

}
