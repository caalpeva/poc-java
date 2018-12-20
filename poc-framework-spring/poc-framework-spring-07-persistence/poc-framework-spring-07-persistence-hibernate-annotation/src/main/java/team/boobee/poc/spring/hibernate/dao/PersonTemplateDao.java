package team.boobee.poc.spring.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boobee.poc.spring.hibernate.model.Person;

public class PersonTemplateDao implements PersonDao {

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void savePerson(Person person) {
		hibernateTemplate.save(person);
	}

	public void updatePerson(Person person) {
		hibernateTemplate.merge(person);
	}

	public Person getPersonById(Integer id) {
		return (Person) hibernateTemplate.get(Person.class, id);
	}

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