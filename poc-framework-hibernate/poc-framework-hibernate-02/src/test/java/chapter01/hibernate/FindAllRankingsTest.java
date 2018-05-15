package chapter01.hibernate;

import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.services.HibernateRankingService;
import team.boobee.poc.framework.hibernate.services.RankingService;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FindAllRankingsTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void findAllRankingsEmptySet() {
        assertEquals(service.getRankingFor("Nobody", SkillType.Climbing), 0);
        assertEquals(service.getRankingFor("Nobody", SkillType.TimeTrial), 0);
        Map<String, Integer> rankings = service.findRankingsFor("Nobody");

        // make sure our dataset size is what we expect: empty
        assertEquals(rankings.size(), 0);
    }

    @Test
    public void findAllRankings() {
        assertEquals(service.getRankingFor("Somebody", SkillType.Climbing), 0);
        assertEquals(service.getRankingFor("Somebody", SkillType.TimeTrial), 0);
        service.addRanking("Somebody", "Nobody", SkillType.Climbing, 9);
        service.addRanking("Somebody", "Nobody", SkillType.Climbing, 7);
        service.addRanking("Somebody", "Nobody", SkillType.TimeTrial, 7);
        service.addRanking("Somebody", "Nobody", SkillType.TimeTrial, 5);
        Map<String, Integer> rankings = service.findRankingsFor("Somebody");

        assertEquals(rankings.size(), 2);
        assertNotNull(rankings.get(SkillType.Climbing.name()));
        assertEquals(rankings.get(SkillType.Climbing.name()), new Integer(8));
        assertNotNull(rankings.get(SkillType.TimeTrial.name()));
        assertEquals(rankings.get(SkillType.TimeTrial.name()), new Integer(6));
    }
}
