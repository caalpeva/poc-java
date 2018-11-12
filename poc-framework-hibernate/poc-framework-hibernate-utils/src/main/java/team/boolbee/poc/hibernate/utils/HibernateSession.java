package team.boolbee.poc.hibernate.utils;

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
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static Session getSession() {
		return getInstance().factory.openSession();
	}
	
	public static void close() {
		getInstance().factory.close();
	}
	
	private static HibernateSession getInstance() {
		return instance;
	}
}