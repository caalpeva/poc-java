package team.boolbee.poc.spring.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

import team.boolbee.poc.spring.hibernate.model.Person;

public class PersonCacheableTemplateDao implements PersonDao {

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@CacheFlush(modelId="pocFlushModel")
	public void savePerson(Person person) {
		hibernateTemplate.save(person);
	}

	public void updatePerson(Person person) {
		hibernateTemplate.merge(person);
	}

	@Cacheable(modelId="pocCacheModel")
	public Person getPersonById(Integer id) {
		return (Person) hibernateTemplate.get(Person.class, id);
	}

	@Cacheable(modelId="pocCacheModel")
	@SuppressWarnings("unchecked")
	public List<Person> list() {
		return hibernateTemplate.loadAll(Person.class);
	}
	
//	public Vehicle findVehicleByPlate(String state, String plateNumber) {
//		List results = hibernateTemplate.find("from " + VEHICLE + " where state = ? and plateNumber = ?",
//				new Object[] { state, plateNumber });
//
//		if (results.size() > 0) {
//			return (Vehicle) results.get(0);
//		}
//
//		return null; // TODO - Should I throw an exception instead?
//	}
}