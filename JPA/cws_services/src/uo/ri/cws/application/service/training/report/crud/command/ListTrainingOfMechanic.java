package uo.ri.cws.application.service.training.report.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.report.TrainingForMechanicRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.VehicleType;

public class ListTrainingOfMechanic
		implements Command<List<TrainingForMechanicRow>> {
	private VehicleTypeRepository repo = Factory.repository.forVehicleType();

	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {
		List<TrainingForMechanicRow> res = new ArrayList<>();
		TrainingForMechanicRow t = null;
		List<VehicleType> vTypes = repo.findAll();
		for (VehicleType v : vTypes) {
			Set<Dedication> dedications = v.getDedications();
			for (Dedication d : dedications) {
				Set<Enrollment> enrollments = d.getCourse().getEnrollments();
				for (Enrollment e : enrollments) {
					t = new TrainingForMechanicRow();
					t.attendedHours = e.getAttendance();
					t.enrolledHours = d.getPercentage() * e.getAttendance()
							/ 100;
					t.vehicleTypeName = v.getName();
				}
			}
			res.add(t);
		}
		return res;
	}

}
