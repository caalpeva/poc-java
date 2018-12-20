package team.boobee.poc.spring.jdbc.test;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boobee.poc.spring.jdbc.dao.PersonDao;
import team.boobee.poc.spring.jdbc.domain.Person;

/**
 * Tests the RantService from the Spring context along with its dependencies.
 * Strictly speaking, this is an integration test, not a unit-test, as it tests
 * the service and its dependencies, as wired in Spring.
 */
public class SimpleJdbcTemplatePersonDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public SimpleJdbcTemplatePersonDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "datasource-context.xml", "jdbc-persistence-context.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("simpleJdbcTemplatePersonDao");

		Person newPerson = new Person();
		newPerson.setFirstName("Marco");
		newPerson.setLastName("Pantani");
		newPerson.setBirthDate(new Date(76, 10, 15));

		personDAO.savePerson(newPerson);
		List<Person> persons = personDAO.list();
		
		for(Person person: persons) {
			System.out.println(person.getId() + " " + person.getFirstName() + " " + person.getBirthDate());
		} // for
		
		assertTrue(persons.contains(newPerson));
	}
}