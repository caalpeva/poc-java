package team.boobee.poc.spring.hibernate.test;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import net.sf.ehcache.CacheManager;
import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.model.Person;

public class PersonTemplateDaoTest extends AbstractDependencyInjectionSpringContextTests {

	public PersonTemplateDaoTest() {
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-datasource.xml", "spring-hibernate.xml" };
	}

	@SuppressWarnings("deprecation")
	public void testAddFirstPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("personDao");

		Person newPerson = new Person();
		newPerson.setFirstName("Alex");
		newPerson.setLastName("Zülle");
		newPerson.setBirthDate(new Date(68, 4, 5));

		personDAO.savePerson(newPerson);

		Person foundPerson = personDAO.getPersonById(newPerson.getId());
		System.out.println(foundPerson.toString());

		foundPerson = personDAO.getPersonById(newPerson.getId());
		System.out.println(foundPerson.toString());
		
		assertNotNull(foundPerson);

		checkCache();
	}
	
	@SuppressWarnings("deprecation")
	public void testAddSecondPerson() throws Exception {
		PersonDao personDAO = (PersonDao) applicationContext.getBean("personDao");

		List<Person> persons = personDAO.list();
		for (Person person : persons) {
			System.out.println(person.toString());
		} // for

		persons = personDAO.list();
		for (Person person : persons) {
			System.out.println(person.toString());
		} // for

		Person newPerson = new Person();
		newPerson.setFirstName("Tony");
		newPerson.setLastName("Rominger");
		newPerson.setBirthDate(new Date(61, 2, 27));
		
		personDAO.savePerson(newPerson);
		
		persons = personDAO.list();
		for (Person person : persons) {
			System.out.println(person.toString());
		} // for
		
		checkCache();
	}
	
	@SuppressWarnings("unchecked")
	private void checkCache() {
		List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
		System.out.println("# of CacheManagers: " + tempManagers.size());
		for (CacheManager tempCM : tempManagers) {
			String[] cacheNames = tempCM.getCacheNames();
			for (int i = 0; i < cacheNames.length; i++) {
				String cacheName = cacheNames[i];
				System.out.println(cacheName + " - " + tempCM.getEhcache(cacheName).getStatistics().toString());
			}
		}
	}
}