package team.boolbee.poc.hibernate.services;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdateRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void updateExistingRanking() {
        service.addRanking("Daryl Dixon", "Carol Peletier", "Decision Making", 6);
        assertEquals(service.getRankingFor("Daryl Dixon", "Decision Making"), 6);
        service.updateRanking("Daryl Dixon", "Carol Peletier", "Decision Making", 7);
        assertEquals(service.getRankingFor("Daryl Dixon", "Decision Making"), 7);
    }

    @Test
    public void updateNonexistentRanking() {
        assertEquals(service.getRankingFor("Carol Peletier", "Decision Making"), 0);
        service.updateRanking("Carol Peletier", "Daryl Dixon", "Decision Making", 7);
        assertEquals(service.getRankingFor("Carol Peletier", "Decision Making"), 7);
    }
}