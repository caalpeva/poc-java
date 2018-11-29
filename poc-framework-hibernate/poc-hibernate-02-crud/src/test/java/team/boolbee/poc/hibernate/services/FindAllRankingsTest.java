package team.boolbee.poc.hibernate.services;

import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FindAllRankingsTest {
    RankingService service = new HibernateRankingService();

//    @Test
//    public void findAllRankingsEmptySet() {
//        assertEquals(service.getRankingFor("Daryl Dixon", "Teamwork"), 0);
//        assertEquals(service.getRankingFor("Daryl Dixon", "Creativity"), 0);
//        Map<String, Integer> rankings = service.findRankingsFor("Daryl Dixon");
//
//        // make sure our dataset size is what we expect: empty
//        assertEquals(rankings.size(), 0);
//    }
//
//    @Test
//    public void findAllRankings() {
//        assertEquals(service.getRankingFor("Michonne", "Teamwork"), 0);
//        assertEquals(service.getRankingFor("Michonne", "Creativity"), 0);
//        service.addRanking("Michonne", "Rick Grimes", "Teamwork", 9);
//        service.addRanking("Michonne", "Maggie Greene", "Teamwork", 7);
//        service.addRanking("Michonne", "Rick Grimes", "Creativity", 7);
//        service.addRanking("Michonne", "Carol Peletier", "Creativity", 5);
//        Map<String, Integer> rankings = service.findRankingsFor("Michonne");
//
//        assertEquals(rankings.size(), 2);
//        assertNotNull(rankings.get("Teamwork"));
//        assertEquals(rankings.get("Teamwork"), new Integer(8));
//        assertNotNull(rankings.get("Creativity"));
//        assertEquals(rankings.get("Creativity"), new Integer(6));
//    }
}
