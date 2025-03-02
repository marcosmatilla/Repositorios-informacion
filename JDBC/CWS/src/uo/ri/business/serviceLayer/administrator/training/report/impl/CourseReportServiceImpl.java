package uo.ri.business.serviceLayer.administrator.training.report.impl;

import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.administrator.training.report.CourseReportService;
import uo.ri.business.transactionScripts.administrator.training.report.ListCertificatesByVehicleType;
import uo.ri.business.transactionScripts.administrator.training.report.ListTrainingByMechanicId;
import uo.ri.business.transactionScripts.administrator.training.report.ListTrainingByVehicleType;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		ListTrainingByMechanicId ltbmi = new ListTrainingByMechanicId(id);
		return ltbmi.execute();
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		ListTrainingByVehicleType ltbvt = new ListTrainingByVehicleType();
		return ltbvt.execute();
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		ListCertificatesByVehicleType lcbvt = new ListCertificatesByVehicleType();
		return lcbvt.execute();
	}

}
