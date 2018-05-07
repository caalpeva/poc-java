package chapter01.hibernate;

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

import team.boobee.poc.framework.hibernate.simple.Person;
import team.boobee.poc.framework.hibernate.simple.Ranking;
import team.boobee.poc.framework.hibernate.simple.Skill;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

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

	@Test
	public void checkRankings() {
		populateRankingData();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session
				.createQuery("from Ranking r " + "where r.subject.name=:name " + "and r.skill.name=:skill");
		query.setString("name", "Bjarne Riis");
		query.setString("skill", "Climbing");

		int sum = 0, count = 0;
		for (Ranking ranking : (List<Ranking>) query.list()) {
			System.out.println(ranking);
			sum += ranking.getRanking();
			count++;
		} // for

		float average = sum / count;
		assertEquals(count, 3);
		assertEquals(average, 7.0f);

		tx.commit();
		session.close();
	}

	@Test
	public void changeRanking() {
		populateRankingData();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Ranking r " + "where r.subject.name=:subject "
				+ "and r.observer.name=:observer " + "and r.skill.name=:skill");

		query.setString("subject", "Bjarne Riis");
		query.setString("observer", "Miguel Indurain");
		query.setString("skill", SkillType.TimeTrial.name());

		Ranking ranking = (Ranking) query.uniqueResult();
		assertNotNull(ranking, "Could not find matching ranking");

		ranking.setRanking(5);
		// select * from Ranking WITH (NOLOCK, NOLOCK)

		tx.commit();
		session.close();
		
		assertEquals(getAverage("Bjarne Riis", SkillType.TimeTrial), 6);
	}

	@Test
	public void zremoveRanking() {
		populateRankingData();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Ranking ranking = findRanking(session, "Bjarne Riis", "Bernard Hinault", SkillType.Climbing);
		assertNotNull(ranking, "Ranking not found");

		session.delete(ranking);

		tx.commit();
		session.close();
		
		assertEquals(getAverage("Bjarne Riis", SkillType.Climbing), 6);
	}

	/***************************************************************************/
	/****************************
	 * METODOS PRIVADOS
	 *****************************/
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

	private void populateRankingData() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		createData(session, "Bjarne Riis", "Pedro Delgado", SkillType.Climbing, 6);
		createData(session, "Bjarne Riis", "Pedro Delgado", SkillType.TimeTrial, 8);
		createData(session, "Bjarne Riis", "Miguel Indurain", SkillType.Climbing, 7);
		createData(session, "Bjarne Riis", "Miguel Indurain", SkillType.TimeTrial, 7);
		createData(session, "Bjarne Riis", "Bernard Hinault", SkillType.Climbing, 8);
		tx.commit();
		session.close();
	}

	private void createData(Session session, String subjectName, String observerName, SkillType skillType, int rank) {
		Person subject = savePerson(session, subjectName);
		Person observer = savePerson(session, observerName);
		Skill skill = saveSkill(session, skillType.name());

		Ranking ranking = new Ranking();
		ranking.setSubject(subject);
		ranking.setObserver(observer);
		ranking.setSkill(skill);
		ranking.setRanking(rank);
		session.save(ranking);
	}

	private Ranking findRanking(Session session, String subject, String observer, SkillType skill) {
		Query query = session.createQuery("from Ranking r " + "where r.subject.name=:subject and "
				+ "r.observer.name=:observer and " + " r.skill.name=:skill");
		query.setString("subject", subject);
		query.setString("observer", observer);
		query.setString("skill", skill.name());
		Ranking ranking = (Ranking) query.uniqueResult();
		return ranking;
	}

	private int getAverage(String subject, SkillType skill) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session
				.createQuery("from Ranking r " + "where r.subject.name=:name " + "and r.skill.name=:skill");
		query.setString("name", subject);
		query.setString("skill", skill.name());
		int sum = 0;
		int count = 0;
		for (Ranking r : (List<Ranking>) query.list()) {
			count++;
			sum += r.getRanking();
			System.out.println(r);
		}
		int average = sum / count;
		tx.commit();
		session.close();
		return average;
	}
}