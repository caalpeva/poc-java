package team.boolbee.poc.spring.jdbc.test;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.jdbc.dao.PersonDao;
import team.boolbee.poc.spring.jdbc.model.Person;

/**
 * Tests the RantService from the Spring context along with its dependencies.
 * Strictly speaking, this is an integration test, not a unit-test, as it tests
 * the service and its dependencies, as wired in Spring.
 */
public class JdbcPersonDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public JdbcPersonDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-datasource.xml", "spring-jdbc.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("jdbcPersonDao");

		Person newPerson = new Person();
		newPerson.setFirstName("Alex");
		newPerson.setLastName("Zülle");
		newPerson.setBirthDate(new Date(68, 4, 5));

		personDAO.savePerson(newPerson);
		List<Person> persons = personDAO.list();
		
		for(Person person: persons) {
			System.out.println(person);
		} // for
		
		assertTrue(persons.contains(newPerson));
	}
}