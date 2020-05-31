package uo.ri.cws.application.service.training.report.crud;

import java.util.List;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.certificates.CertificateDto;
import uo.ri.cws.application.service.training.report.CourseReportService;
import uo.ri.cws.application.service.training.report.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.report.TrainingHoursRow;
import uo.ri.cws.application.service.training.report.crud.command.ListTrainingByVehicleType;
import uo.ri.cws.application.service.training.report.crud.command.ListTrainingOfMechanic;
import uo.ri.cws.application.service.training.report.crud.command.ListCertificationsByVehicleType;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseReportServiceImpl implements CourseReportService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(String id)
			throws BusinessException {
		return executor.execute(new ListTrainingOfMechanic());
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic()
			throws BusinessException {
		return executor.execute(new ListTrainingByVehicleType());
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType()
			throws BusinessException {
		return executor.execute(new ListCertificationsByVehicleType());
	}

}
