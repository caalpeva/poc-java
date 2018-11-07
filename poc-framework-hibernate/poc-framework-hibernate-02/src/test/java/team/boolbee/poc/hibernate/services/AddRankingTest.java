package team.boolbee.poc.hibernate.services;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class AddRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void addRanking() {
        service.addRanking("Glenn Rhee", "Rick Grimes", "Mule", 8);
        assertEquals(service.getRankingFor("Glenn Rhee", "Mule"), 8);
    }	
}
