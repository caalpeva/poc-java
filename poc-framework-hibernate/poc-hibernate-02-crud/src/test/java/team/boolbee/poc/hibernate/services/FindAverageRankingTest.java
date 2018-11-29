package team.boolbee.poc.hibernate.services;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.services.HibernateRankingService;
import team.boolbee.poc.hibernate.services.RankingService;

public class FindAverageRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void validateRankingAverage() {
        service.addRanking("Rick Grimes", "Daryl Dixon", "Leadership", 5);
        service.addRanking("Rick Grimes", "Glenn Rhee", "Leadership", 6);
        service.addRanking("Rick Grimes", "Maggie Greene", "Leadership", 7);
        assertEquals(service.getRankingFor("Rick Grimes", "Leadership"), 6);
        service.addRanking("Rick Grimes", "Carol Peletier", "Leadership", 8);
        service.addRanking("Rick Grimes", "Michonne", "Leadership", 9);
        assertEquals(service.getRankingFor("Rick Grimes", "Leadership"), 7);
    }
}
