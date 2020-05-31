package uo.ri.cws.application.service.training.attendance.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;

public class ListAttendanceToCourse implements Command<List<EnrollmentDto>> {
	private EnrollmentRepository repoE = Factory.repository.forEnrollment();
	private CourseRepository repoC = Factory.repository.forCourse();
	private String id;

	public ListAttendanceToCourse(String id) {
		this.id = id;
	}

	@Override
	public List<EnrollmentDto> execute() throws BusinessException {
		Optional<Course> oe = repoC.findById(id);
		BusinessCheck.exists(oe, "The course does not exist");

		BusinessCheck.isNotEmpty(id, "id is empty");

		List<EnrollmentDto> res = new ArrayList<>();
		List<Enrollment> enrollments = repoE.findAttendanceInCourse(id);

		for (Enrollment e : enrollments) {
			EnrollmentDto enroll = new EnrollmentDto();
			enroll.mechanic = new MechanicDto();
			enroll.attendance = e.getAttendance();
			enroll.mechanic.name = e.getMechanic().getName();
			enroll.mechanic.surname = e.getMechanic().getSurname();
			enroll.passed = e.isPassed();
			res.add(enroll);

		}

		return res;
	}

}
