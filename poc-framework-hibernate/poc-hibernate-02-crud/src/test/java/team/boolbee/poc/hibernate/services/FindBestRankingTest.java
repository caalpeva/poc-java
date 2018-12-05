package team.boolbee.poc.hibernate.services;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.model.Person;
import team.boolbee.poc.hibernate.services.HibernateRankingService;
import team.boolbee.poc.hibernate.services.RankingService;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class FindBestRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void findBestForNonexistentSkill() {
        Person p = service.findBestPersonFor("no skill");
        assertNull(p);
    }

    @Test
    public void findBestForSkill() {
        service.addRanking("Daryl Dixon", "Rick Grimes", "Teamwork", 6);
        service.addRanking("Daryl Dixon", "Michonne", "Teamwork", 8);
        service.addRanking("Maggie Greene", "Rick Grimes", "Teamwork", 7);
        service.addRanking("Maggie Greene", "Michonne", "Teamwork", 7);
        service.addRanking("Carol Peletier", "Rick Grimes", "Teamwork", 7);
        service.addRanking("Carol Peletier", "Michonne", "Teamwork", 9);
        // data that should not factor in!
        service.addRanking("Daryl Dixon", "Michonne", "Decision Making", 5);

        Person p = service.findBestPersonFor("Teamwork");
        assertEquals(p.getName(), "Carol Peletier");
    }
}