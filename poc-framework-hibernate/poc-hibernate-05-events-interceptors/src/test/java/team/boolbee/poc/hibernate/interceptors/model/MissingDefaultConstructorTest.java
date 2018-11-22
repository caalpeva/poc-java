package team.boolbee.poc.hibernate.interceptors.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MissingDefaultConstructorTest {

	SessionFactory factory;

	@BeforeSuite
	public void setup() {
		Configuration configuration = new Configuration();
		configuration.configure();

		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	@Test
	public void saveMissingDefaultConstructorObject() {
		MissingDefaultConstructorObject object = new MissingDefaultConstructorObject("Hello, world", 2L);
		Session session = factory.openSession();
		session.beginTransaction();

		session.persist(object);

		session.getTransaction().commit();
		session.close();
	}
	
	@Test(dependsOnMethods = "saveMissingDefaultConstructorObject", expectedExceptions = org.hibernate.InstantiationException.class)
	public void testMissingDefaultConstructorObjectFailure() {
		Session session = factory.openSession();
		MissingDefaultConstructorObject object = (MissingDefaultConstructorObject) session.get(MissingDefaultConstructorObject.class, 1L);
		System.out.println(object);
		session.close();
	}
}