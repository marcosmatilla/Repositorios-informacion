package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class CourseJpaRepository extends BaseJpaRepository<Course>
		implements CourseRepository {

	/**
	 * Método para buscar un curso por el codigo
	 */
	@Override
	public Optional<Course> findCourseByCode(String code) {
		return Jpa.getManager()
				.createNamedQuery("Course.findCourseByCode", Course.class)
				.setParameter(1, code).getResultList().stream().findFirst();
	}

}
