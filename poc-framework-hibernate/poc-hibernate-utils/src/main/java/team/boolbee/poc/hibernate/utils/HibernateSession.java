package team.boolbee.poc.hibernate.utils;

import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSession {

	private static final HibernateSession instance = new HibernateSession();
	private final SessionFactory factory;

	private HibernateSession() {
		Configuration configuration = new Configuration();
		configuration.configure();
		//configuration.setInterceptor(interceptor); // To apply interceptor using session factory
		
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session getSession() {
		return getInstance().factory.openSession();
	}

	public static Session getSession(Interceptor interceptor) {
		return getInstance().factory
				.withOptions()
				.interceptor(interceptor) // add interceptor to Session
				.openSession();
	}

	public static void close() {
		getInstance().factory.close();
	}

	private static HibernateSession getInstance() {
		return instance;
	}
}