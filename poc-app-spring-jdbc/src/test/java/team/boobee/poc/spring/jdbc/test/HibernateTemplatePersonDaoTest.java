package team.boobee.poc.spring.jdbc.test;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boobee.poc.spring.hibernate.dao.RantDao;
import team.boobee.poc.spring.hibernate.domain.Motorist;
import team.boobee.poc.spring.hibernate.domain.Rant;
import team.boobee.poc.spring.hibernate.domain.Vehicle;

/**
 * Tests the RantService from the Spring context along with its dependencies.
 * Strictly speaking, this is an integration test, not a unit-test, as it tests
 * the service and its dependencies, as wired in Spring.
 */
public class HibernateTemplatePersonDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public HibernateTemplatePersonDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "datasource-context.xml", "hibernate-context.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		RantDao rantDAO = (RantDao) applicationContext.getBean("rantDao");

		Rant newRant = new Rant();
		newRant.setRantText("TEST RANT TEXT");
		newRant.setPostedDate(new Date(90, 9, 1));
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);
		vehicle.setPlateNumber("ABC123");
		vehicle.setState("TX");
		newRant.setVehicle(vehicle);
		rantDAO.saveRant(newRant);
		
		Vehicle newVehicle = rantDAO.findVehicleByPlate(newRant.getVehicle().getState(),
				newRant.getVehicle().getPlateNumber());

		//assertTrue(newVehicle.getRants().contains(newRant));
	}
}