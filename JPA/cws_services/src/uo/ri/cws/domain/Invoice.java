package uo.ri.cws.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Assert;
import alb.util.math.Round;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

@Entity
@Table(name = "TINVOICES")
public class Invoice extends BaseEntity {

	public enum InvoiceStatus {
		NOT_YET_PAID, PAID
	}

	@Column(unique = true)
	private Long number;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double amount;
	private double vat;

	@Enumerated(EnumType.STRING)
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

	@OneToMany(mappedBy = "invoice")
	private Set<WorkOrder> workOrders = new HashSet<>();

	@OneToMany(mappedBy = "invoice")
	private Set<Charge> charges = new HashSet<>();

	public Invoice() {

	}

	public Invoice(Long number) {
		super();
		this.number = number;
		this.date = new Date();
	}

	public Invoice(Long number, Date date) {
		super();
		Assert.isNotNull(number);
		Assert.isNotNull(date);

		this.number = number;

		this.date = new Date(date.getTime());
	}

	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number);
		Assert.isNotNull(number);
		Assert.isNotNull(workOrders);
		for (WorkOrder w : workOrders)
			this.addWorkOrder(w);
	}

	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
		this(number, workOrders);
		Assert.isNotNull(number);
		Assert.isNotNull(date);
		Assert.isNotNull(workOrders);
		this.date = new Date(date.getTime());
	}

	public Long getNumber() {
		return number;
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}

	public double getAmount() {
		computeAmount();
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workOrders);
	}

	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	public Set<Charge> getCharges() {
		return new HashSet<>(charges);
	}

	Set<Charge> _getCharges() {
		return charges;
	}

	@Override
	public String toString() {
		return "Invoice [number=" + number + ", date=" + date + ", amount="
				+ amount + ", vat=" + vat + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	/**
	 * Computed amount and vat (vat depends on the date)
	 */
	private void computeAmount() {

		try {
			String fecha_vat_ = "1/7/2012";
			SimpleDateFormat formateadorFecha = new SimpleDateFormat(
					"dd/MM/yyyy");
			Date fecha_vat = formateadorFecha.parse(fecha_vat_);
			if (fecha_vat.compareTo(date) == 1) {
				setVat(18.0);
			} else {
				setVat(21.0);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		amount = 0L;
		for (WorkOrder w : workOrders) {
			amount += w.getAmount();
		}
		amount *= 1L + vat / 100L;
		amount = Round.twoCents(amount);
	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount
	 * and vat
	 * 
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		if (!this.getStatus().equals(InvoiceStatus.NOT_YET_PAID)) {
			throw new IllegalStateException(
					"La factura no puede estar sin abonar.");
		}
		if (!workOrder.getStatus().equals(WorkOrderStatus.FINISHED)) {
			throw new IllegalStateException(
					"La averia no puede estar abierta.");
		}
		Associations.ToInvoice.link(workOrder, this);
		workOrder.markAsInvoiced();
		this.computeAmount();

	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 * 
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void removeWorkOrder(WorkOrder workOrder) {
		if (!this.getStatus().equals(InvoiceStatus.NOT_YET_PAID)) {
			throw new IllegalStateException(
					"La factura no puede estar sin abonar.");
		}
		Associations.ToInvoice.unlink(workOrder, this);
		workOrder.markBackToFinished();
		this.computeAmount();
	}

	/**
	 * Marks the invoice as PAID, but
	 * 
	 * @throws IllegalStateException if - Is already settled - Or the amounts
	 *                               paid with charges to payment means do not
	 *                               cover the total of the invoice
	 */
	public void settle() {
		this.status = InvoiceStatus.PAID;
	}

}
