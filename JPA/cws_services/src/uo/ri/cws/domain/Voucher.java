package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TVOUCHERS")
public class Voucher extends PaymentMean {
	@Column(unique = true)
	private String code;

	private double available;
	private String description;

	 Voucher() {

	}

	public Voucher(String code) {
		super();
		Argument.isNotEmpty(code);
		Argument.isNotNull(code);
		this.code = code;
	}

	public Voucher(String code, double available, String description) {
		this(code);
		Argument.isNotEmpty(code);
		Argument.isNotNull(code);
		Argument.isNotNull(available);
		Argument.isNotEmpty(description);
		Argument.isNotNull(description);
		this.available = available;
		this.description = description;
	}

	public Voucher(String code, Client client) {
		this(code);
		Argument.isNotEmpty(code);
		Argument.isNotNull(code);
		Argument.isNotNull(client);
		Associations.Pay.link(this, client);
	}

	public Voucher(String code, double available) {
		Argument.isNotEmpty(code);
		Argument.isNotNull(code);
		Argument.isNotNull(available);
		this.code = code;
		this.available = available;
	}

	/**
	 * Augments the accumulated and decrements the available
	 * 
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {
		this.available = available - getAccumulated();
	}

	void actualizarDisponible() {
		this.available -= this.getAccumulated();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAvailable() {
		return available;
	}

	public double getDisponible() {
		return getAvailable();
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescripcion(String description) {
		this.description = description;
	}

}
