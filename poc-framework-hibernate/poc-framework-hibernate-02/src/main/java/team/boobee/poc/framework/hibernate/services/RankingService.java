package team.boobee.poc.framework.hibernate.services;

import java.util.Map;

import team.boobee.poc.framework.hibernate.simple.Person;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

public interface RankingService {

	public int getRankingFor(String subject, SkillType skill);
	public void addRanking(String subject, String observer, SkillType skill, int ranking);
	public void updateRanking(String subject, String observer, SkillType skill, int ranking);
	public void removeRanking(String subject, String observer, SkillType skill);
	public Map<String, Integer> findRankingsFor(String subject);
	public Person findBestPersonFor(SkillType skillType);
	
}