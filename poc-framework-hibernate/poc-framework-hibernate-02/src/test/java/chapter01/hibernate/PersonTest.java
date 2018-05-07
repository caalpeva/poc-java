package chapter01.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.simple.Person;

public class PersonTest {
    SessionFactory factory;

    @BeforeClass
    public void setup() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
        srBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void savePerson() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Person person = new Person();
        person.setName("Jan Ullrich");

        session.save(person);

        tx.commit();
        session.close();
    }

}