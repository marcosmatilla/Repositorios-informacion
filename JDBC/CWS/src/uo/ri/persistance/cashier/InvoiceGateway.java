package uo.ri.persistance.cashier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import uo.ri.business.exception.BusinessException;

public interface InvoiceGateway {
	void setConnection(Connection c);

	void testRepairs(List<Long> workOrderIDS) throws SQLException, BusinessException;

	void updateWorkOrderStatus(List<Long> breakdownIds, String status) throws SQLException;

	void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) throws SQLException;

	long createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) throws SQLException;

	long getGeneratedKey(long numberInvoice) throws SQLException;

	Long generateInvoiceNumber() throws SQLException;

	void updateWorkorderTotal(Long workOrderID, double total) throws SQLException;

	double checkTotalParts(Long workOrderID) throws SQLException;

	double checkTotalLabor(Long workOrderID) throws BusinessException, SQLException;

	double calculateTotalInvoice(List<Long> workOrderIDS) throws BusinessException, SQLException;
}
