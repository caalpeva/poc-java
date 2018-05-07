package chapter01.hibernate;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.services.HibernateRankingService;
import team.boobee.poc.framework.hibernate.services.RankingService;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

public class RankingServiceTest {
	RankingService service = new HibernateRankingService();

	@Test
	public void addRanking() {
		service.addRanking("J. C. Smell", "Drew Lombardo", SkillType.Climbing, 8);
		assertEquals(service.getRankingFor("J. C. Smell", SkillType.Climbing), 8);
	}

	@Test
	public void updateExistingRanking() {
		service.addRanking("Gene Showrama", "Scottball Most", SkillType.TimeTrial, 6);
		assertEquals(service.getRankingFor("Gene Showrama", SkillType.TimeTrial), 6);
		service.updateRanking("Gene Showrama", "Scottball Most", SkillType.TimeTrial, 7);
		assertEquals(service.getRankingFor("Gene Showrama", SkillType.TimeTrial), 7);
	}

	@Test
	public void updateNonexistentRanking() {
		assertEquals(service.getRankingFor("Scottball Most", SkillType.TimeTrial), 0);
		service.updateRanking("Scottball Most", "Gene Showrama", SkillType.TimeTrial, 7);
		assertEquals(service.getRankingFor("Scottball Most", SkillType.TimeTrial), 7);
	}

}