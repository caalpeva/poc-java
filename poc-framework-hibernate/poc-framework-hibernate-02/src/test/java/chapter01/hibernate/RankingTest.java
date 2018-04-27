package chapter01.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.simple.Person;
import team.boobee.poc.framework.hibernate.simple.Ranking;
import team.boobee.poc.framework.hibernate.simple.Skill;

public class RankingTest {
    SessionFactory factory;

    @BeforeMethod
    public void setup() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
        srBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    @AfterMethod
    public void shutdown() {
    	factory.close();
    }
    
    @Test
    public void saveRanking() {
    	Session session = factory.openSession();
    	Transaction tx = session.beginTransaction();
    	
        Person subject = savePerson(session, "Marco Pantani");
    	Person observer = savePerson(session, "Pedro Delgado");
    	Skill skill = saveSkill(session, "Climbing");
        
    	Ranking ranking = new Ranking();
    	ranking.setSubject(subject);
    	ranking.setObserver(observer);
    	ranking.setSkill(skill);
    	ranking.setRanking(8);
        session.save(ranking);

        tx.commit();
        session.close();
    }

    /***************************************************************************/
    /**************************** METODOS PRIVADOS *****************************/
    /***************************************************************************/
    
    private Person savePerson(Session session, String name) {
        Person person = findPerson(session, name);
        if (person == null) {
            person = new Person();
            person.setName(name);
            session.save(person);
        }
        
        return person;
    }
    
    private Person findPerson(Session session, String name) {
        Query query = session.createQuery("from Person p where p.name=:name");
        query.setParameter("name", name);
        Person person = (Person) query.uniqueResult();
        return person;
    }
    
    private Skill saveSkill(Session session, String skillName) {
        Skill skill = findSkill(session, skillName);
        if (skill == null) {
            skill = new Skill();
            skill.setName(skillName);
            session.save(skill);
        }
        return skill;
    }
    
    private Skill findSkill(Session session, String name) {
        Query query = session.createQuery("from Skill s where s.name=:name");
        query.setParameter("name", name);
        Skill skill = (Skill) query.uniqueResult();
        return skill;
    }
}