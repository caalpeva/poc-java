package team.boolbee.poc.hibernate.services;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class RemoveRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void removeRanking() {
    	service.addRanking("Glenn Rhee", "Carol Peletier", "Teamwork", 8);
        assertEquals(service.getRankingFor("Glenn Rhee", "Teamwork"), 8);
        service.removeRanking("Glenn Rhee", "Carol Peletier", "Teamwork");
        assertEquals(service.getRankingFor("Glenn Rhee", "Teamwork"), 0);
    }

    @Test
    public void removeNonexistentRanking() {
        service.removeRanking("Michonne", "Maggie Greene", "Teamwork");
    }
}