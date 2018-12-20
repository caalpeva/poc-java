package team.boobee.poc.spring.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import team.boobee.poc.spring.hibernate.model.Person;

public class PersonHibernateSessionDao implements PersonDao {

	// injected
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void savePerson(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(person);
		tx.commit();
		session.close();
	}

	public void updatePerson(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.merge(person);
		tx.commit();
		session.close();
	}

	public Person getPersonById(Integer id) {
		Session session = sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, id);		
		session.close();
		return person;
	}

	@SuppressWarnings("unchecked")
	public List<Person> list() {
		Session session = sessionFactory.openSession();
		List<Person> persons = session.createCriteria(Person.class).list();		
		session.close();
		return persons;
	}
}