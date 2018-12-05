package team.boolbee.poc.hibernate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.model.Person;

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
        person.setName("Glenn Rhee");
        person.setBirthDate(createDate(1982, 7, 23));
        
        session.save(person);
        
        tx.commit();
        session.close();
    }
    
    @Test(dependsOnMethods = "savePerson")
    public void readPerson() {
        Session session = factory.openSession();
        
        Person person = (Person) session.get(Person.class, 1L);
        System.out.println("Age: " + person.getAge());
        assertEquals(person.getAge(), 36);
        session.close();
    }
    
    private Calendar createDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, 0, 0, 0);
		return calendar; 
	}
}