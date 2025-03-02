package uo.ri.cws.associations;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.SparePart;
import uo.ri.cws.domain.Substitution;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class InvoiceTests {
	private Mechanic mechanic;
	private WorkOrder workOrder;
	private Intervention intervention;
	private SparePart sparePart;
	private Vehicle vehicle;
	private VehicleType vehicleType;
	private Client client;
	private Invoice invoice;

	@Before
	public void setUp() {
		client = new Client("dni-cliente", "nombre", "apellidos");
		vehicle = new Vehicle("1234 GJI", "seat", "ibiza");
		Associations.Own.link(client, vehicle);

		vehicleType = new VehicleType("coche", 50.0);
		Associations.Classify.link(vehicleType, vehicle);

		workOrder = new WorkOrder(vehicle, "falla la junta la trocla");
		mechanic = new Mechanic("dni-mecanico", "nombre", "apellidos");

		workOrder.assignTo(mechanic); // averia pasa a asignada

		intervention = new Intervention(mechanic, workOrder, 60);

		sparePart = new SparePart("R1001", "junta la trocla", 100.0);
		new Substitution(sparePart, intervention, 2);

		workOrder.markAsFinished(); // changes status & compute price

		invoice = new Invoice(0L, Dates.today());
		invoice.addWorkOrder(workOrder);
	}

	@Test
	public void testLinkOnInvoice() {
		assertTrue(invoice.getWorkOrders().contains(workOrder));
		assertTrue(invoice.getAmount() > 0.0);

		assertTrue(workOrder.getInvoice() == invoice);
		assertTrue(workOrder.getStatus().equals(WorkOrderStatus.INVOICED));
	}

	@Test
	public void testUnlinkOnInvoice() {
		invoice.removeWorkOrder(workOrder);

		assertTrue(!invoice.getWorkOrders().contains(workOrder));
		assertTrue(invoice.getWorkOrders().size() == 0);
		assertTrue(invoice.getAmount() == 0.0);

		assertTrue(workOrder.getInvoice() == null);
		assertTrue(workOrder.getStatus().equals(WorkOrderStatus.FINISHED));
	}

	@Test
	public void testSafeReturn() {
		Set<WorkOrder> facturadas = invoice.getWorkOrders();
		facturadas.remove(workOrder);

		assertTrue(facturadas.size() == 0);
		assertTrue("It must be a copy of the collection or a read-only version",
				invoice.getWorkOrders().size() == 1);
	}

}
