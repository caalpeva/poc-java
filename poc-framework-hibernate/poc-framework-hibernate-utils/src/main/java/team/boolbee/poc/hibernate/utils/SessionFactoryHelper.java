package team.boolbee.poc.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryHelper {

	private static final SessionFactoryHelper instance = new SessionFactoryHelper();
	private final SessionFactory factory;
	
	private SessionFactoryHelper() {
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
	
	private static SessionFactoryHelper getInstance() {
		return instance;
	}
}