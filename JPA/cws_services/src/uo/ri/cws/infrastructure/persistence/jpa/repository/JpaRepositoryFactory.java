package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.ClientRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.InterventionRepository;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.PaymentMeanRepository;
import uo.ri.cws.application.repository.RepositoryFactory;
import uo.ri.cws.application.repository.SparePartRepository;
import uo.ri.cws.application.repository.TrainingRepository;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public MechanicRepository forMechanic() {
		return new MechanicJpaRepository();
	}

	@Override
	public WorkOrderRepository forWorkOrder() {
		return new WorkOrderJpaRepository();
	}

	@Override
	public PaymentMeanRepository forPaymentMean() {
		return new PaymentMeanJpaRepository();
	}

	@Override
	public InvoiceRepository forInvoice() {
		return new InvoiceJpaRepository();
	}

	@Override
	public ClientRepository forClient() {
		return new ClientJpaRepository();
	}

	@Override
	public SparePartRepository forSparePart() {
		return new SparePartJpaRepository();
	}

	@Override
	public InterventionRepository forIntervention() {
		return new InterventionJpaRepository();
	}

	@Override
	public VehicleRepository forVehicle() {
		return new VehicleJpaRepository();
	}

	@Override
	public VehicleTypeRepository forVehicleType() {
		return new VehicleTypeJpaRepository();
	}

	@Override
	public TrainingRepository forTraining() {
		return new TrainingJpaRepository();
	}

	@Override
	public DedicationRepository forDedication() {
		return new DedicationJpaRepository();
	}

	@Override
	public CertificateRepository forCerticiate() {
		return new CertificateJpaRepository();
	}

	@Override
	public CourseRepository forCourse() {
		return new CourseJpaRepository();
	}

	@Override
	public EnrollmentRepository forEnrollment() {
		return new EnrollmentJpaRepository();
	}

}
