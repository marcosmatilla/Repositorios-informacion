package uo.ri.cws.application.service.training.course.crud.command;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	private CourseDto course;
	private CourseRepository repo = Factory.repository.forCourse();
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();

	public AddCourse(CourseDto course) {
		this.course = course;
	}

	@Override
	public CourseDto execute() throws BusinessException {
		checkDto(course);
		checkDoesNotExist(course.code);
		checkPercentages();
		chekVehicleType(course.percentages.keySet());

		Course c = new Course(course.code, course.name, course.description,
				course.startDate, course.endDate, course.hours);

		repo.add(c);
		course.id = c.getId();
		
		Map<VehicleType, Integer> dedications = new HashMap<VehicleType, Integer>();
		for (Entry<String, Integer> entry : course.percentages.entrySet())
		{
			checkVehicle(entry.getKey());
			VehicleType vehicleType = repoV.findById(entry.getKey()).get();
			System.out.println(vehicleType);

			dedications.put(vehicleType, entry.getValue());
		}
		c.addDedications(dedications);
		
		return course;
	}

	

	private void checkVehicle(String key) throws BusinessException {
		Optional<VehicleType> vehicleType = repoV.findById(key);
		BusinessCheck.exists(vehicleType,
				"The vehicleType does not exist");
	}

	private void chekVehicleType(Set<String> keySet) throws BusinessException {
		for (String k : keySet) {
			Optional<VehicleType> v = repoV.findById(k);
			BusinessCheck.exists(v, "vehicletype does not exist");

		}
	}

	private void checkPercentages() throws BusinessException {
		int res = 0;
		Collection<Integer> n = course.percentages.values();
		for (Integer i : n) {
			BusinessCheck.isTrue(i > 0);
			res += i;
		}
		System.out.println(res);
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
