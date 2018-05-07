package team.boobee.poc.framework.hibernate.services;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import team.boobee.poc.framework.hibernate.simple.Person;
import team.boobee.poc.framework.hibernate.simple.Ranking;
import team.boobee.poc.framework.hibernate.simple.Skill;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;
import team.boolbee.poc.hibernate.utils.SessionUtil;

public class HibernateRankingService implements RankingService {

	@Override
	public int getRankingFor(String subject, SkillType skill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addRanking(String subject, String observer, SkillType skill, int ranking) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		addRanking(session, subject, observer, skill, ranking);

		tx.commit();
		session.close();

	}

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

	private void addRanking(Session session, String subjectName, String observerName, SkillType skillType, int rank) {
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

	@Override
	public void updateRanking(String subject, String observer, SkillType skill, int ranking) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRanking(String subject, String observer, SkillType skill) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Integer> findRankingsFor(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findBestPersonFor(SkillType skillType) {
		// TODO Auto-generated method stub
		return null;
	}

}
