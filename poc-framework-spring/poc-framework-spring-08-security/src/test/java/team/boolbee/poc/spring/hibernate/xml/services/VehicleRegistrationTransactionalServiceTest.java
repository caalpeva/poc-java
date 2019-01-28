package team.boolbee.poc.spring.hibernate.xml.services;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;
import team.boolbee.poc.spring.hibernate.model.VehicleType;
import team.boolbee.poc.spring.hibernate.services.VehicleRegistrationService;

public class VehicleRegistrationTransactionalServiceTest extends AbstractDependencyInjectionSpringContextTests {

	public VehicleRegistrationTransactionalServiceTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] {
				"spring-context.xml",
				"spring-datasource.xml",
				"spring-hibernate.xml",
				"spring-jmx-server-mbean.xml",
				"spring-scheduler.xml",
				"spring-tx.xml",
				"spring-email.xml"
			};
	}

	@SuppressWarnings("deprecation")
	public void testAddPersonsWithVehicles() {
		VehicleRegistrationService registrationServiceDAO = (VehicleRegistrationService) applicationContext
				.getBean("vehicleRegistrationService");

		Vehicle vehicle = new Vehicle();
		vehicle.setPlateNumber("AHR 7811");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.AUTOMOBILE);

		Person person = new Person();
		person.setName("Dan");
		person.setSurname("Aykroyd");
		person.setBirthDate(new Date(52, 6, 1));
		person.setEmail("daykroyd@mailinator.com");
		person.addVehicle(vehicle);

		registrationServiceDAO.register(person);

		List<Person> persons = registrationServiceDAO.getPersons();

		for (Person currentPerson : persons) {
			System.out.println(currentPerson);
		} // for

		assertTrue(persons.contains(person));

		vehicle = new Vehicle();
		vehicle.setPlateNumber("2107 PZG");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.MOTORCYCLE);

		person = new Person();
		person.setName("Bill");
		person.setSurname("Murray");
		person.setBirthDate(new Date(50, 8, 21));
		person.setEmail("bmurray@mailinator.com");
		person.addVehicle(vehicle);

		try {
			registrationServiceDAO.register(person);
		} catch (DataIntegrityViolationException e) {
			persons = registrationServiceDAO.getPersons();
			for (Person currentPerson : persons) {
				System.out.println(currentPerson);
			} // for

			assertFalse(persons.contains(person));
		}

		persons = registrationServiceDAO.getPersons();

		for (Person currentPerson : persons) {
			System.out.println(currentPerson);
		} // for

		List<Vehicle> vehicles = registrationServiceDAO.getVehiclesForDay(new Date());
		for (Vehicle currentVehicle : vehicles) {
			System.out.println(currentVehicle.getPlateNumber() + " " + currentVehicle.getRegistrationDate());
		} // for
	}
}