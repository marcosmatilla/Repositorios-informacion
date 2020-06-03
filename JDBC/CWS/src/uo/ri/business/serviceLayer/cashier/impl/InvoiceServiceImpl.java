package uo.ri.business.serviceLayer.cashier.impl;

import java.util.List;
import java.util.Map;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.cashier.InvoiceService;
import uo.ri.business.transactionScripts.cashier.WorkOrderBilling;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public InvoiceDto createInvoiceFor(List<Long> idsAveria) throws BusinessException {
		WorkOrderBilling wob = new WorkOrderBilling(idsAveria);
		return wob.execute();
	}

	@Override
	public InvoiceDto findInvoice(Long numeroFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long idFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> cargos) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
