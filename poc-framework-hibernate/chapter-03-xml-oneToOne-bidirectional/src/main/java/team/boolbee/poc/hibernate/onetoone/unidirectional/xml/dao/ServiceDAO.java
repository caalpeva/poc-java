package team.boolbee.poc.hibernate.onetoone.unidirectional.xml.dao;

import java.util.List;

import org.hibernate.Session;
import team.boolbee.poc.hibernate.onetoone.unidirectional.xml.model.Service;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class ServiceDAO implements ServiceDAOInterface {

	public Service selectById(long id) {
		Session session = HibernateSession.getSession();
	    Service service = (Service) session.get(Service.class, id);
	    session.close();
	    return service;
	}

	@SuppressWarnings("unchecked")
	public List<Service> selectAll() {
	    Session session = HibernateSession.getSession();
	    List<Service> services = session.createQuery("from Service").list();
	    session.close();
	    return services;
	}
	
	public void insert(Service service) {
	    Session session = HibernateSession.getSession();
	    session.beginTransaction();
	
	    session.persist(service);
	         
	    session.getTransaction().commit();
	    session.close();

	}
	
	public void update(Service service) {
	    Session session = HibernateSession.getSession();
	    session.beginTransaction();
	 
	    session.merge(service);
		 
	    session.getTransaction().commit();
	    session.close();
	}
	
	public void delete(Service service) {
	    Session session = HibernateSession.getSession();
	    session.beginTransaction();
	    
	    session.delete(service);
	 
	    session.getTransaction().commit();
	    session.close();
	}
}