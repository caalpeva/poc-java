package team.boolbee.poc.spring.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boolbee.poc.spring.hibernate.model.Person;

public class PersonTemplateDao implements PersonDao {

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int getPersonCount() {
		return list().size();
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
}