package team.boolbee.poc.spring.hibernate.xml.test;

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
public class PersonHibernateSessionDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public PersonHibernateSessionDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-datasource.xml", "spring-hibernate.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("hibernateSessionPersonDao");

		Person newPerson = new Person();
		newPerson.setFirstName("Chris");
		newPerson.setLastName("Froome");
		newPerson.setBirthDate(new Date(85, 5, 20));

		personDAO.savePerson(newPerson);
		List<Person> persons = personDAO.list();
		
		for(Person person: persons) {
			System.out.println(person.getId() + " " + person.getFirstName() + " " + person.getBirthDate());
		} // for
		
		assertTrue(persons.contains(newPerson));
	}
}