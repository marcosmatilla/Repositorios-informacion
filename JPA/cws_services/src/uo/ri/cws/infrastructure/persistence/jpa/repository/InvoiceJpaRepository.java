package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class InvoiceJpaRepository extends BaseJpaRepository<Invoice>
		implements InvoiceRepository {

	/**
	 * Metodo que nos busca una factura por el numero
	 */
	@Override
	public Optional<Invoice> findByNumber(Long numero) {
		return Jpa.getManager()
				.createNamedQuery("Invoice.findByNumber", Invoice.class)
				.setParameter(1, numero).getResultStream().findFirst();
	}

	/**
	 * Metodo que nos devuelve el siguiente numero para la factura
	 */
	@Override
	public Long getNextInvoiceNumber() {
		return Jpa.getManager()
				.createNamedQuery("Invoice.getNextInvoiceNumber", Long.class)
				.getSingleResult();
	}

}
