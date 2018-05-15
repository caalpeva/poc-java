package chapter01.hibernate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import team.boobee.poc.framework.hibernate.services.HibernateRankingService;
import team.boobee.poc.framework.hibernate.services.RankingService;
import team.boobee.poc.framework.hibernate.simple.Person;
import team.boobee.poc.framework.hibernate.simple.domain.SkillType;

public class FindBestRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void findBestForNonexistentSkill() {
        Person p = service.findBestPersonFor(null);
        assertNull(p);
    }

    @Test
    public void findBestForSkill() {
        service.addRanking("S1", "O1", SkillType.Climbing, 6);
        service.addRanking("S1", "O2", SkillType.Climbing, 8);
        service.addRanking("S2", "O1", SkillType.Climbing, 5);
        service.addRanking("S2", "O2", SkillType.Climbing, 7);
        service.addRanking("S3", "O1", SkillType.Climbing, 7);
        service.addRanking("S3", "O2", SkillType.Climbing, 9);
        // data that should not factor in!
        service.addRanking("S1", "O2", SkillType.TimeTrial, 2);

        Person p = service.findBestPersonFor(SkillType.Climbing);
        assertEquals(p.getName(), "S3");
    }

}
