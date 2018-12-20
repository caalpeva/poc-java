package team.boobee.poc.spring.hibernate.test;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.model.Person;

public class PersonTemplateDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public PersonTemplateDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "datasource-context.xml", "hibernate-context.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("personDao");

		Person newPerson = new Person();
		newPerson.setFirstName("Alex");
		newPerson.setLastName("Zülle");
		newPerson.setBirthDate(new Date(68, 4, 5));

		personDAO.savePerson(newPerson);
		List<Person> persons = personDAO.list();
		
		for(Person person: persons) {
			System.out.println(person.getId() + " " + person.getFirstName() + " " + person.getBirthDate());
		} // for
		
		assertTrue(persons.contains(newPerson));
	}
}