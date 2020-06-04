package uo.ri.business.dto;

import java.sql.Timestamp;

public class WorkOrderDto {

	public Long id;

	public Long vehicleId;
	public String description;
	public Timestamp date;
	public double total;
	public String status;

	// might be null
	public Long mechanicId;
	public Long invoiceId;

}
