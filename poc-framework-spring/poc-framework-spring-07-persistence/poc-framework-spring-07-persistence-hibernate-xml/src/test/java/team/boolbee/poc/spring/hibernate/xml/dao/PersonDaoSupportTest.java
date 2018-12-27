package team.boolbee.poc.spring.hibernate.xml.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.model.Person;

/**
 * Tests the RantService from the Spring context along with its dependencies.
 * Strictly speaking, this is an integration test, not a unit-test, as it tests
 * the service and its dependencies, as wired in Spring.
 */
public class PersonDaoSupportTest extends AbstractDependencyInjectionSpringContextTests {

	public PersonDaoSupportTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-datasource.xml", "spring-hibernate.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("personDaoSupport");

		Person newPerson = new Person();
		newPerson.setName("Cadel");
		newPerson.setSurname("Evans");
		newPerson.setBirthDate(new Date(77, 2, 14));

		personDAO.savePerson(newPerson);
		List<Person> persons = personDAO.list();
		
		for(Person person: persons) {
			System.out.println(person);
		} // for
		
		assertTrue(persons.contains(newPerson));
	}
}