package uo.ri.business.serviceLayer.cashier;

import java.util.List;
import java.util.Map;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoiceService {

	InvoiceDto createInvoiceFor(List<Long> workOrderIds) throws BusinessException;

	InvoiceDto findInvoice(Long numeroFactura) throws BusinessException;

	List<PaymentMeanDto> findPayMethodsForInvoice(Long invoiceId) throws BusinessException;

	void settleInvoice(Long invoiceId, Map<Long, Double> cargos) throws BusinessException;

	List<WorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException;

}
