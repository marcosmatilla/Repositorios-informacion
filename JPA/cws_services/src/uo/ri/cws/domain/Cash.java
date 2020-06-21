package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TCASHES")
public class Cash extends PaymentMean {

	public Cash(Client client) {
		Argument.isNotNull(client);
		Associations.Pay.link(this, client);
	}

	 Cash() {

	}

}
