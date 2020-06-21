package uo.ri.cws.application.service.training.report.crud.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.report.TrainingForMechanicRow;
import uo.ri.cws.application.util.command.Command;

public class ListTrainingOfMechanic implements Command<List<TrainingForMechanicRow>> {
	

	public ListTrainingOfMechanic() {
	
	}

	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {
		return null;
	}

}
