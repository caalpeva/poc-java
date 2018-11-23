package team.boolbee.poc.hibernate.interceptors.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.interceptors.InstantiationInterceptor;

public class SessionFactoryScopedInterceptorTest {

	SessionFactory factory;

	@BeforeSuite
	public void setup() {
		Configuration configuration = new Configuration();
		configuration.configure();

		// Configure interceptor using session factory
		configuration.setInterceptor(new InstantiationInterceptor());

		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	@Test
	public void saveLoadMissingDefaultCtorObject() {
		MissingDefaultCtorObject object = new MissingDefaultCtorObject("No default constructor");
		Session session = factory.openSession();
		session.beginTransaction();

		session.persist(object);

		session.getTransaction().commit();
		session.close();

		session = factory.openSession();
		object = (MissingDefaultCtorObject) session.get(MissingDefaultCtorObject.class,
				object.getId());
		System.out.println(object);
		
		session.close();
	}
}