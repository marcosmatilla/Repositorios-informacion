package uo.ri.cws.application.service.training.report.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.report.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class ListTrainingByVehicleType implements Command<List<TrainingHoursRow>> {
	private VehicleTypeRepository repoV = Factory.repository.forVehicleType();
	private MechanicRepository repoM = Factory.repository.forMechanic();

	@Override
	public List<TrainingHoursRow> execute() throws BusinessException {

		List<TrainingHoursRow> res = new ArrayList<TrainingHoursRow>();

		List<VehicleType> vTypes = repoV.findAll();
		List<Mechanic> mechanics = repoM.findAll();
		for (VehicleType v : vTypes) {
			for (Mechanic m : mechanics) {
				Set<Enrollment> enrollments = m.getEnrollmentsFor(v);
				if (!enrollments.isEmpty()) {
					int hours = 0;
					TrainingHoursRow t = new TrainingHoursRow();
					for (Enrollment e : enrollments) {
						hours += e.getAttendedHoursFor(v);
					}
					t.enrolledHours = hours;
					t.mechanicFullName = m.getName() + " " + m.getSurname();
					t.vehicleTypeName = v.getName();
					res.add(t);
				}

			}
		}

		return res;

	}

}
