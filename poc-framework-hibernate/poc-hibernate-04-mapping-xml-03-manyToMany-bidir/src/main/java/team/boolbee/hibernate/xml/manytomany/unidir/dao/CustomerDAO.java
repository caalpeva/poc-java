package team.boolbee.hibernate.xml.manytomany.unidir.dao;

import java.util.List;

import org.hibernate.Session;

import team.boolbee.hibernate.xml.manytomany.unidir.model.Customer;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class CustomerDAO implements CustomerDAOInterface {

	public Customer selectById(Long id) {
		Session session = HibernateSession.getSession();

		Customer customer = (Customer) session.get(Customer.class, id);

		session.close();
		return customer;
	}

	public Customer selectByName(String name) {
	    Session session = HibernateSession.getSession();
	 
	    Customer customer = (Customer) session.createQuery(
	    		String.format("from Customer c where c.name = '%s'", name)).uniqueResult();
	    
	    session.close();
	    return customer;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> selectAll() {
		Session session = HibernateSession.getSession();

		List<Customer> customeres = session.createQuery("from Customer").list();

		session.close();
		return customeres;
	}

	public void insert(Customer customer) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.persist(customer);

		session.getTransaction().commit();
		session.close();

	}

	public void update(Customer customer) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.merge(customer);

		session.getTransaction().commit();
		session.close();
	}

	public void delete(Customer customer) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.delete(customer);

		session.getTransaction().commit();
		session.close();
	}

}