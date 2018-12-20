package team.boobee.poc.spring.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import team.boobee.poc.spring.hibernate.model.Person;

public class PersonDaoSupport extends HibernateDaoSupport implements PersonDao {

	public void savePerson(Person person) {
		getHibernateTemplate().save(person);
	}

	public void updatePerson(Person person) {
		getHibernateTemplate().merge(person);
	}

	public Person getPersonById(Integer id) {
		return (Person) getHibernateTemplate().get(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Person> list() {
		return getHibernateTemplate().loadAll(Person.class);
	}
}