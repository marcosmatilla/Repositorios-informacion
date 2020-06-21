package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;
import uo.ri.cws.domain.Invoice.InvoiceStatus;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "INVOICE_ID",
		"PAYMENTMEN_ID" }) }, name = "TCHARGES")
public class Charge extends BaseEntity {

	@ManyToOne
	private Invoice invoice;

	@ManyToOne
	private PaymentMean paymentMean;

	private double amount;

	 Charge() {

	}

	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		Argument.isNotNull(invoice);
		Argument.isNotNull(paymentMean);

		if (paymentMean instanceof Voucher) {
			if (((Voucher) paymentMean).getAvailable() < amount) {
				throw new IllegalStateException(
						"No hay suficiente dinero para realizar el pago.");
			}
		}

		this.amount = amount;

		paymentMean.setAccumulated(paymentMean.getAccumulated() + amount);
		if (paymentMean instanceof Voucher) {
			((Voucher) paymentMean).actualizarDisponible();
		}

		Associations.Charges.link(invoice, this, paymentMean);
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * 
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		if (this.invoice.getStatus().equals(InvoiceStatus.PAID)) {
			throw new IllegalStateException(
					"La factura no puede estar ABONADA.");
		}

		paymentMean.pay(-amount);

		Associations.Charges.unlink(this);
	}

	public Invoice getInvoice() {
		return invoice;
	}

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}
}