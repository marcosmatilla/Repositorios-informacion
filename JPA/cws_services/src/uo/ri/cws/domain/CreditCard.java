package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Argument;
import alb.util.date.Dates;

@Entity
@Table(name = "TCREDITCARDS")
public class CreditCard extends PaymentMean {

	@Column(unique = true)
	private String number;

	private String type;

	@Temporal(TemporalType.DATE)
	private Date validThru;

	 CreditCard() {

	}

	public CreditCard(String number) {
		super();
		Argument.isNotNull(number);
		Argument.isNotEmpty(number);
		this.number = number;
	}

	public CreditCard(String number, String type, Date validThru) {
		this(number);
		Argument.isNotNull(number);
		Argument.isNotEmpty(number);
		Argument.isNotNull(type);
		Argument.isNotEmpty(type);
		Argument.isNotNull(validThru);
		if (validThru.getTime() < Dates.today().getTime()) {
			throw new IllegalStateException("La tarjeta no es valida");
		}
		this.type = type;
		this.validThru = new Date(validThru.getTime());
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", type=" + type
				+ ", validThru=" + validThru + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((validThru == null) ? 0 : validThru.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (validThru == null) {
			if (other.validThru != null)
				return false;
		} else if (!validThru.equals(other.validThru))
			return false;
		return true;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getValidThru() {
		return validThru;
	}

	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}

}
