package team.boolbee.poc.hibernate.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;

public class GenericInterceptorDAO<T> {
	
	private Interceptor interceptor;
	protected Session session;
	
	public GenericInterceptorDAO(Interceptor interceptor) {
		this.interceptor = interceptor;
	}
	
	public Session getSession() {
		return session;
	}
	
	protected void startTransaction() {
		session = HibernateSession.getSession(interceptor);
		session.getTransaction().begin();
	}
	
	protected void endTransaction() {
		session.getTransaction().commit();
		session.close();
	}
	
	protected void handleException(HibernateException e) throws HibernateException {
		session.getTransaction().rollback();
		throw e;
	}
	
	public void insert(T entity) throws HibernateException {
		try {
			startTransaction();
			session.persist(entity);
			//session.flush();
		} catch(HibernateException e) {
			handleException(e);
		} finally {
			endTransaction();
		}
	}
	
	public void update(T entity) throws HibernateException {
		try {
			startTransaction();
			session.merge(entity);
			//session.flush();
		} catch(HibernateException e) {
			handleException(e);
		} finally {
			endTransaction();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T selectById(Serializable id, Class<T> entityClass) throws HibernateException {
		T obj = null;
		try {
			session = HibernateSession.getSession(interceptor);
			obj = (T) session.get(entityClass, id);
		} finally {
			session.close();
		}
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> selectAll(Class<T> entityClass) throws HibernateException {
		List<T> result = null;
		try {
			session = HibernateSession.getSession(interceptor);
			result = session.createQuery("FROM " + entityClass.getSimpleName()).list();
			//result = session.createCriteria(entityClass).list();
		} finally {
			session.close();
		}
		
		return result;
	}
	
	public void delete(T entity) throws HibernateException {
		try {
			startTransaction();
			session.delete(entity);
			//session.flush();
		} catch(HibernateException e) {
			handleException(e);
		} finally {
			endTransaction();
		}
	}

}