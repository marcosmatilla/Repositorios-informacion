package uo.ri.cws.application.service.training.report.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.report.TrainingForMechanicRow;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class ListTrainingOfMechanic implements Command<List<TrainingForMechanicRow>> {
	String id;
	private MechanicRepository mrepo = Factory.repository.forMechanic();
	private EnrollmentRepository repo = Factory.repository.forEnrollment();
	private CourseRepository crepo = Factory.repository.forCourse();

	public ListTrainingOfMechanic(String id) {
		this.id = id;
	}

	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {
		Optional<Mechanic> om = mrepo.findById(id);
		BusinessCheck.exists(om, "The mechanic does not exist");

		List<TrainingForMechanicRow> l = new ArrayList<TrainingForMechanicRow>();
		List<Enrollment> i = repo.findTrainingByMechanicId(id);

		for (Enrollment e : i) {
			Optional<Course> ocs = crepo.findById(e.getCourse().getId());

			getDedications(l, e, ocs);
		}

		return l;
	}

	private void getDedications(List<TrainingForMechanicRow> l, Enrollment e,
			Optional<Course> cs) {
		if (cs.isPresent()) {
			for (Dedication d : cs.get().getDedications()) {
				TrainingForMechanicRow m = new TrainingForMechanicRow();
				m.enrolledHours = cs.get().getHours() * d.getPercentage() / 100;
				m.attendedHours = m.enrolledHours * e.getAttendance() / 100;
				m.vehicleTypeName = d.getVehicleType().getName();
				l.add(m);
			}
		}
	}

}
