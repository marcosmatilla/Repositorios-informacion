package uo.ri.business.transactionScripts.administrator.training.courseattendance;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.administrator.mechanic.MechanicGateway;
import uo.ri.persistance.administrator.training.course.CourseGateway;
import uo.ri.persistance.administrator.training.courseattendance.CourseAttendanceGateway;

public class RegisterCourseAttendance {
	private EnrollmentDto enrollment;
	
	public RegisterCourseAttendance(EnrollmentDto enrollment) {
		this.enrollment = enrollment;
	}
	
	/**
	 * Registers the attendance of a mechanic to a course.
	 * 
	 * @param dto, with this fields filled: mechanicId, courseId, attendance and the
	 *        boolean passed.
	 *
	 * @return the same dto but with the id field set to the generated UUID value
	 *
	 * @throws BusinessException if: - the mechanic id does not exit, or - the
	 *                           course id does not exist, or - there already is
	 *                           another enrollment registered for the same mechanic
	 *                           and course, or - the attendance is under 85% and
	 *                           the course is marked as passed, or - the course is
	 *                           not yet finished, or <- IGNORE THIS, complicates
	 *                           testing - the value for percentage is not in the
	 *                           range 0..100
	 */
	public EnrollmentDto execute() throws BusinessException {
		
		try (Connection c = Jdbc.createThreadConnection()) {
			c.setAutoCommit(false);
			CourseAttendanceGateway cag = PersistenceFactory.getCourseAttendanceGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway(); //For mechanic id
			CourseGateway cg = PersistenceFactory.getCourseGateway(); //For course id
			cag.setConnection(c);
			mg.setConnection(c);
			cg.setConnection(c);
			if(mg.findById(enrollment.mechanicId) == null) { //Mecanico no existe
				c.rollback();
				throw new BusinessException("mechanic does not exist");
			}
			if(cg.findCourseById(enrollment.courseId) == null) {
				c.rollback();
				throw new BusinessException("course does not exist");
			}
			if(cag.findEnrollmentSameMechanicAndCourse(enrollment.mechanicId, enrollment.courseId) != null) { //Otro enrollemnte al mismo mecanicoy curso
				c.rollback();
				throw new BusinessException("there is another same course with same mechanic assigned ");
			}
			if(enrollment.passed && enrollment.attendance < 85) { //Attendance < 85 y curso marcada como passed
				c.rollback();
				throw new BusinessException("attendance must be more than 85 and can not be passed");
			}
			if(!(enrollment.attendance >= 0 && enrollment.attendance <= 100)) {
				c.rollback();
				throw new BusinessException("attendance is not between 0 and 100");
			}
			cag.registerNew(enrollment);
			c.commit();
			
			EnrollmentDto en = cag.findEnrollmentSameMechanicAndCourse(enrollment.mechanicId, enrollment.courseId);
			enrollment.id = en.id;
			
			return enrollment;
		}
		catch (SQLException e) {
			throw new RuntimeException("Error de conexión");
		}
	
	}
}
