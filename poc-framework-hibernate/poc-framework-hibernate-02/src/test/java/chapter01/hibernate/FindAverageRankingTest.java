package chapter01.hibernate;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.services.HibernateRankingService;
import team.boobee.poc.framework.hibernate.services.RankingService;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

public class FindAverageRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void validateRankingAverage() {
        service.addRanking("A", "B", SkillType.Climbing, 4);
        service.addRanking("A", "B", SkillType.Climbing, 5);
        service.addRanking("A", "B", SkillType.Climbing, 6);
        assertEquals(service.getRankingFor("A", SkillType.Climbing), 5);
        service.addRanking("A", "B", SkillType.Climbing, 7);
        service.addRanking("A", "B", SkillType.Climbing, 8);
        assertEquals(service.getRankingFor("A", SkillType.Climbing), 6);
    }
}
