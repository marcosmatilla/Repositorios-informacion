package uo.ri.cws.application.service.training.report.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.TrainingRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.report.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.VehicleType;

public class ListTrainingByVehicleType implements Command<List<TrainingHoursRow>> {
	private TrainingRepository repo = Factory.repository.forTraining();

	@Override
	public List<TrainingHoursRow> execute() throws BusinessException {
		List<VehicleType> vTypes = repo.findTrainingByVehicleTypeId();

		List<TrainingHoursRow> res = new ArrayList<TrainingHoursRow>();
		Set<Enrollment> enrollments = null;
		Set<Dedication> dedications = null;

		for (VehicleType v : vTypes) {
			int totalHoras = 0;
			dedications = v.getDedications();
			for (Dedication d : dedications) {
				enrollments = d.getCourse().getEnrollments();
				for (Enrollment e : enrollments) {
					if (checkMech(res, e, v.getName())) {
						for (TrainingHoursRow tr : res) {
							if (tr.mechanicFullName
									.equals(e.getMechanic().getName() + " " + e.getMechanic().getSurname())) {
								totalHoras += (d.getPercentage() * e.getAttendance() * d.getCourse().getHours())
										/ 10000;
							}
						}
					} else {
						TrainingHoursRow t = new TrainingHoursRow();
						t.mechanicFullName = e.getMechanic().getName() + " " + e.getMechanic().getSurname();
						t.vehicleTypeName = v.getName();
						totalHoras = (d.getPercentage() * e.getAttendance() * d.getCourse().getHours()) / 10000;
						t.enrolledHours = totalHoras;
						res.add(t);
					}
				}
			}
		}

		return res;

	}

	private boolean checkMech(List<TrainingHoursRow> res, Enrollment e, String vname) {
		for (TrainingHoursRow tr : res) {
			if (tr.mechanicFullName.equals(e.getMechanic().getName() + " " + e.getMechanic().getSurname())
					&& tr.vehicleTypeName.equals(vname)) {
				return true;
			}
		}
		return false;
	}
}
