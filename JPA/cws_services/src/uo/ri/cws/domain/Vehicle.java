package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TVEHICLES")
public class Vehicle extends BaseEntity {
	@Column(unique = true)
	private String plateNumber;

//	@Column(name = "brand")
	private String make;

	private String model;

	@ManyToOne
	private Client client;

	@ManyToOne
	private VehicleType vehicleType;

	@OneToMany(mappedBy = "vehicle")
	private Set<WorkOrder> workorders = new HashSet<>();

	public Vehicle() {

	}

	public Vehicle(String plateNumber) {
		super();
		Argument.isNotNull(plateNumber);
		Argument.isNotEmpty(plateNumber);
		this.plateNumber = plateNumber;
	}

	public Vehicle(String plateNumber, String make, String model) {
		Argument.isNotNull(plateNumber);
		Argument.isNotEmpty(plateNumber);
		Argument.isNotNull(make);
		Argument.isNotEmpty(make);
		Argument.isNotNull(model);
		Argument.isNotEmpty(model);
		this.plateNumber = plateNumber;
		this.make = make;
		this.model = model;
	}

	public Client getClient() {
		return client;
	}

	void _setClient(Client client) {
		this.client = client;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	Set<WorkOrder> _getWorkOrders() {
		return workorders;
	}

	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workorders);
	}

	void _setWorkorders(Set<WorkOrder> workorders) {
		this.workorders = workorders;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((plateNumber == null) ? 0 : plateNumber.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
				+ ", model=" + model + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

}
