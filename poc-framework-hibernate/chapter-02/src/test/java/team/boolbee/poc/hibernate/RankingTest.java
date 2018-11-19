package team.boolbee.poc.hibernate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

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

import team.boolbee.poc.hibernate.model.Person;
import team.boolbee.poc.hibernate.model.Ranking;
import team.boolbee.poc.hibernate.model.Skill;

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

		Person subject = savePerson(session, "Glenn Rhee");
		Person observer = savePerson(session, "Rick Grimes");
		Skill skill = saveSkill(session, "Adaptability");

		Ranking ranking = new Ranking();
		ranking.setSubject(subject);
		ranking.setObserver(observer);
		ranking.setSkill(skill);
		ranking.setRanking(8);
		session.save(ranking);

		tx.commit();
		session.close();
	}

	@Test
	public void checkRankings() {
		populateRankingData();
		assertEquals(getAverage("Glenn Rhee", "Adaptability"), 7);
	}

	@Test
	public void changeRanking() {
		populateRankingData();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:subject "
				+ "and r.observer.name=:observer "
				+ "and r.skill.name=:skill");

		query.setString("subject", "Glenn Rhee");
		query.setString("observer", "Daryl Dixon");
		query.setString("skill", "Adaptability");

		Ranking ranking = (Ranking) query.uniqueResult();
		assertNotNull(ranking, "Could not find matching ranking");

		ranking.setRanking(9);
		// select * from Ranking WITH (NOLOCK, NOLOCK)

		tx.commit();
		session.close();
		
		assertEquals(getAverage("Glenn Rhee", "Adaptability"), 8);
	}

	@Test
	public void removeRanking() {
		populateRankingData();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Ranking ranking = findRanking(session, "Glenn Rhee", "Daryl Dixon", "Adaptability");
		assertNotNull(ranking, "Ranking not found");

		session.delete(ranking);

		tx.commit();
		session.close();
		
		assertEquals(getAverage("Glenn Rhee", "Adaptability"), 7);
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
		return (Person) query.uniqueResult();
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
		return (Skill) query.uniqueResult();
	}

	private void populateRankingData() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		createData(session, "Glenn Rhee", "Daryl Dixon", "Adaptability", 6);
		createData(session, "Glenn Rhee", "Maggie Greene", "Adaptability", 7);
		createData(session, "Glenn Rhee", "Rick Grimes", "Adaptability", 8);
		tx.commit();
		session.close();
	}

	private void createData(Session session, String subjectName, String observerName, String skillName, int rank) {
		Person subject = savePerson(session, subjectName);
		Person observer = savePerson(session, observerName);
		Skill skill = saveSkill(session, skillName);

		Ranking ranking = new Ranking();
		ranking.setSubject(subject);
		ranking.setObserver(observer);
		ranking.setSkill(skill);
		ranking.setRanking(rank);
		session.save(ranking);
	}

	private Ranking findRanking(Session session, String subject, String observer, String skill) {
		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:subject and "
				+ "r.observer.name=:observer and "
				+ "r.skill.name=:skill");
		query.setString("subject", subject);
		query.setString("observer", observer);
		query.setString("skill", skill);
		return (Ranking) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	private int getAverage(String subject, String skill) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:name "
				+ "and r.skill.name=:skill");
		query.setString("name", subject);
		query.setString("skill", skill);
		int sum = 0;
		int count = 0;
		for (Ranking r : (List<Ranking>) query.list()) {
			count++;
			sum += r.getRanking();
			System.out.println(r);
		}
		
		tx.commit();
		session.close();
		
		return count == 0 ? 0 : sum / count;
	}
}