package uo.ri.cws.application.service.training.course.crud.command;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import alb.util.assertion.Argument;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.course.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.VehicleType;

public class AddCourse implements Command<CourseDto> {
	private CourseDto dto;
	private CourseRepository repo = Factory.repository.forCourse();
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();

	public AddCourse(CourseDto course) {
		this.dto = course;
	}

	@Override
	public CourseDto execute() throws BusinessException {
		checkDto(dto);
		checkDoesNotExist(dto.code);
		checkPercentages();
		chekVehicleType(dto.percentages.keySet());

		Course c = new Course(dto.code, dto.name, dto.description,
				dto.startDate, dto.endDate, dto.hours);

		repo.add(c);
		dto.id = c.getId();
		return dto;
	}

	private void chekVehicleType(Set<String> keySet) throws BusinessException {
		for (String k : keySet) {
			Optional<VehicleType> v = repoV.findById(k);
			BusinessCheck.exists(v, "vehicletype does not exist");

		}
	}

	private void checkPercentages() throws BusinessException {
		int res = 0;
		Collection<Integer> n = dto.percentages.values();
		for (Integer i : n) {
			BusinessCheck.isTrue(i > 0);
			res += i;
		}
		Argument.isTrue(res == 100);
	}

	private void checkDoesNotExist(String code) throws BusinessException {
		Optional<Course> oc = repo.findCourseByCode(code);
		BusinessCheck.isFalse(oc.isPresent(), "code already exist");

	}

	private void checkDto(CourseDto dto) throws BusinessException {
		BusinessCheck.isNotEmpty(dto.code, "Empty code");
		BusinessCheck.isNotEmpty(dto.name, "Empty name");
		BusinessCheck.isNotEmpty(dto.description, "Empty description");
		BusinessCheck.isNotNull(dto.startDate, "Empty start date");
		BusinessCheck.isNotNull(dto.endDate, "Empty end date");
		BusinessCheck.isTrue(dto.hours > 0, "Negative hours");

		BusinessCheck.isTrue(dto.startDate.before(dto.endDate),
				"End cannot be before the beginning ");

		Date today = new Date();
		BusinessCheck.isTrue(dto.startDate.after(today),
				"Invalid date, start date before today");
	}

}
