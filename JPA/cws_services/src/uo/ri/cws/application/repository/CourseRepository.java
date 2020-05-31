package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;

public interface CourseRepository extends Repository<Course> {

	/**
	 * Busca el curso por el codigo pasado por parametro
	 * 
	 * @param code para busca el course
	 * @return curso si lo encuentra si no, empty
	 */
	Optional<Course> findCourseByCode(String code);

	/**
	 * Lista todos los cursos
	 * 
	 * @return
	 */
	List<Course> findAll();

}
