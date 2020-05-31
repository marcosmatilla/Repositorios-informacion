package uo.ri.cws.domain;

public class Associations {

	public static class Own {

		public static void link(Client client, Vehicle vehicle) {
			vehicle._setClient(client);
			client._getVehicles().add(vehicle);
		}

		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle._setClient(null);
		}

	}

	public static class Classify {

		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicle._setVehicleType(vehicleType);
			vehicleType._getVehicles().add(vehicle);

		}

		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle._setVehicleType(null);

		}

	}

	public static class Pay {

		public static void link(PaymentMean paymentMean, Client client) {
			paymentMean._setClient(client);
			client._getPaymentMeans().add(paymentMean);

		}

		public static void unlink(Client client, PaymentMean paymentMean) {
			client._getPaymentMeans().remove(paymentMean);
			paymentMean._setClient(null);
		}

	}

	public static class Order {

		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			workOrder._setVehicle(vehicle);
			vehicle._getWorkOrders().add(workOrder);

		}

		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);

		}

	}

	public static class ToInvoice {

		public static void link(WorkOrder workOrder, Invoice invoice) {
			workOrder._setInvoice(invoice);
			invoice._getWorkOrders().add(workOrder);
		}

		public static void unlink(WorkOrder workOrder, Invoice invoice) {
			invoice._getWorkOrders().remove(workOrder);
			workOrder._setInvoice(null);

		}

	}

	public static class Charges {

		public static void link(Invoice invoice, Charge charge,
				PaymentMean paymentMean) {
			charge._setPaymentMean(paymentMean);
			charge._setInvoice(invoice);
			paymentMean._getCharges().add(charge);
			invoice._getCharges().add(charge);

		}

		public static void unlink(Charge charge) {
			Invoice invoice = charge.getInvoice();
			PaymentMean paymentMean = charge.getPaymentMean();
			invoice._getCharges().remove(charge);
			paymentMean._getCharges().remove(charge);
			charge._setInvoice(null);
			charge._setPaymentMean(null);

		}

	}

	public static class Assign {

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getWorkOrders().add(workOrder);
		}

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getWorkOrders().remove(workOrder);
			workOrder._setMechanic(null);
		}

	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention,
				Mechanic mechanic) {
			intervention._setWorkOrder(workOrder);
			intervention._setMechanic(mechanic);
			workOrder._getInterventions().add(intervention);
			mechanic._getInterventions().add(intervention);
		}

		public static void unlink(Intervention intervention) {
			Mechanic mechanic = intervention.getMechanic();
			WorkOrder workOrder = intervention.getWorkOrder();

			mechanic._getInterventions().remove(intervention);
			workOrder._getInterventions().remove(intervention);

			intervention._setMechanic(null);
			intervention._setWorkOrder(null);

		}

	}

	public static class Sustitute {

		public static void link(SparePart sparePart, Substitution substitution,
				Intervention intervention) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);
			intervention._getSubstitutions().add(substitution);
			sparePart._getSubstitutions().add(substitution);

		}

		public static void unlink(Substitution substitution) {
			Intervention intervention = substitution.getIntervention();
			SparePart sparePart = substitution.getSparePart();

			intervention._getSubstitutions().remove(substitution);
			sparePart._getSubstitutions().remove(substitution);
			substitution._setIntervention(null);
			substitution._setSparePart(null);

		}

	}

	public static class Certify {
		public static void link(Certificate certificate, Mechanic mechanic,
				VehicleType vehicleType) {
			certificate._setMechanic(mechanic);
			certificate._setVehicleType(vehicleType);
			mechanic._getCertificates().add(certificate);
			vehicleType._getCertificates().add(certificate);
		}
	}

	public static class Dedicate {
		public static void link(VehicleType vehicleType, Course course,
				Dedication dedication) {
			dedication._setCourse(course);
			dedication._setVehicleType(vehicleType);
			vehicleType._getDedications().add(dedication);
			course._getDedications().add(dedication);
		}

		public static void unlink(Dedication dedication) {
			Course course = dedication.getCourse();
			VehicleType vehicleType = dedication.getVehicleType();

			course._getDedications().remove(dedication);
			vehicleType._getDedications().remove(dedication);
			dedication._setCourse(null);
			dedication._setVehicleType(null);
		}
	}

	public static class Enroll {
		public static void link(Mechanic mechanic, Course course,
				Enrollment enrollment) {
			enrollment._setCourse(course);
			enrollment._setMechanic(mechanic);
			mechanic._getEnrollments().add(enrollment);
			course._getEnrollments().add(enrollment);
		}
	}

}
