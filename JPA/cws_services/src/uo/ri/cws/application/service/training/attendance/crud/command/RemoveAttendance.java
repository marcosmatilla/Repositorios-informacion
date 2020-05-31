package uo.ri.cws.application.service.training.attendance.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;

public class RemoveAttendance implements Command<Void> {
	private EnrollmentRepository repoE = Factory.repository.forEnrollment();
	private String id;

	public RemoveAttendance(String id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Enrollment> oe = repoE.findById(id);
		BusinessCheck.exists(oe, "Id does not exist");

		Enrollment e = oe.get();
		repoE.remove(e);

		return null;
	}

}
