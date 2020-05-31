package uo.ri.cws.application.service.training.attendance.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class RegisterAttendance implements Command<EnrollmentDto> {
	private MechanicRepository repoM = Factory.repository.forMechanic();
	private CourseRepository repoC = Factory.repository.forCourse();
	private EnrollmentRepository repoE = Factory.repository.forEnrollment();

	private EnrollmentDto dto;

	public RegisterAttendance(EnrollmentDto dto) {
		this.dto = dto;
	}

	@Override
	public EnrollmentDto execute() throws BusinessException {
		BusinessCheck.isNotEmpty(dto.mechanicId, "mechanic id is empty");
		BusinessCheck.isNotEmpty(dto.courseId, "course id is empty");

		Optional<Mechanic> om = repoM.findById(dto.mechanicId);
		BusinessCheck.exists(om, "The mechanic does not exist");

		Optional<Course> oc = repoC.findById(dto.courseId);
		BusinessCheck.exists(oc, "The course does not exist");

		BusinessCheck.isFalse((dto.attendance < 85 && dto.passed),
				"passed can not be true wiht attendance < 85");

		BusinessCheck.isTrue(dto.attendance > 0 && dto.attendance <= 100,
				"attendance cannot be greater than 100 or less than 0");
		Course c = oc.get();
		Mechanic m = om.get();

		Optional<Enrollment> oe = repoE
				.findEnrollForCourseAndMechanic(dto.courseId, dto.mechanicId);
		if (oe.isPresent()) {
			throw new BusinessException(
					"mechanic is already enrolled in this course");
		}

		Enrollment e = new Enrollment(m, c, dto.attendance, dto.passed);

		repoE.add(e);

		dto.id = e.getId();

		return dto;

	}

}
