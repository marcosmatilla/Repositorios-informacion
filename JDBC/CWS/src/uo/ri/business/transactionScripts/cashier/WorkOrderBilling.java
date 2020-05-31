package uo.ri.business.transactionScripts.cashier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistance.InvoiceGateway;

public class WorkOrderBilling {

	private Connection con;

	private List<Long> workOrderIds;

	private InvoiceGateway mg = PersistenceFactory.getInvoiceGateway();

	public WorkOrderBilling(List<Long> workOrderIDS) {
		this.workOrderIds = workOrderIDS;
	}

	public InvoiceDto execute() throws BusinessException {
		InvoiceDto res = new InvoiceDto();
		try {
			con = Jdbc.createThreadConnection();
			mg.setConnection(con);
			con.setAutoCommit(false);

			mg.testRepairs(workOrderIds);

			res.number = mg.generateInvoiceNumber();
			res.date = Dates.today();
			double amount = mg.calculateTotalInvoice(workOrderIds);
			res.vat = vatPercentage(amount, res.date);
			res.total = amount * (1 + res.vat / 100);
			res.total = Round.twoCents(res.total);

			long idInvoice = mg.createInvoice(res.number, res.date, res.vat, res.total);
			mg.linkWorkorderInvoice(idInvoice, workOrderIds);
			mg.updateWorkOrderStatus(workOrderIds, "INVOICED");

			con.commit();

			return res;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		}

	}

	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

}