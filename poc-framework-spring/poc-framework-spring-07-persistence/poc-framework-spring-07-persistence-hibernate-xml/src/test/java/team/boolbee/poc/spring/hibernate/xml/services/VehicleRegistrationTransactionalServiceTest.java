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
		return new String[] { "spring-datasource.xml", "spring-hibernate.xml", "spring-tx.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPersonsWithVehicles() {
		VehicleRegistrationService registrationServiceDAO = (VehicleRegistrationService) applicationContext
				.getBean("vehicleRegistrationService");

		Vehicle vehicle = new Vehicle();
		vehicle.setPlateNumber("4653 NSX");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.AUTOMOBILE);

		Person person = new Person();
		person.setName("Alex");
		person.setSurname("Zülle");
		person.setBirthDate(new Date(68, 4, 5));
		person.addVehicle(vehicle);

		registrationServiceDAO.register(person);

		List<Person> persons = registrationServiceDAO.getPersons();

		for (Person currentPerson : persons) {
			System.out.println(currentPerson);
		} // for

		assertTrue(persons.contains(person));

		vehicle = new Vehicle();
		vehicle.setPlateNumber("4653 NSX");
		vehicle.setRegistrationDate(new Date());
		vehicle.setType(VehicleType.MOTORCYCLE);

		person = new Person();
		person.setName("Cadel");
		person.setSurname("Evans");
		person.setBirthDate(new Date(77, 2, 14));
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
	}
}