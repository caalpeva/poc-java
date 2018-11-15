package team.boolbee.hibernate.xml.onetomany.unidir.model;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.testng.annotations.Test;

import team.boolbee.hibernate.xml.onetomany.unidir.dao.TeamDAO;
import team.boolbee.poc.hibernate.utils.HibernateSession;


public class OneToOneManyXmlTest {
	
    @Test
    public void testOneToOneMappedByXml() {
    	Rider player1 = new Rider();
		player1.setName("Lance Armstrong");
		player1.setNumber(21);
		
		Rider player2 = new Rider();
		player2.setName("Andreas Kloden");
		player2.setNumber(24);
		
		Team team1 = new Team();
		team1.setName("Team RadioShack");
		team1.addRider(player1);
		team1.addRider(player2);
    	
		TeamDAO teamDAO = new TeamDAO();
		teamDAO.insert(team1);
		
    	Rider player3 = new Rider();
		player3.setName("Alberto Contador");
		player3.setNumber(1);
		
		Rider player4 = new Rider();
		player4.setName("Alexandre Vinokourov");
		player4.setNumber(9);

		Team team2 = new Team();
		team2.setName("Astana");
		team2.addRider(player3);
		team2.addRider(player4);
		
		teamDAO.insert(team2);
		
		Rider player5 = new Rider();
		player5.setName("Andy Schleck");
		player5.setNumber(11);
		
		Rider player6 = new Rider();
		player6.setName("Fränk Schleck");
		player6.setNumber(16);
		
		Team team3 = new Team();
		team3.setName("Team Saxo Bank");
		team3.addRider(player5);
		team3.addRider(player6);

		teamDAO.insert(team3);
		
		Rider player7 = new Rider();
		player7.setName("Cadel Evans");
		player7.setNumber(121);
		
		Rider player8 = new Rider();
		player8.setName("George Hincapie");
		player8.setNumber(126);
		
		Team team4 = new Team();
		team4.setName("BMC Racing");
		team4.addRider(player7);
		team4.addRider(player8);

		teamDAO.insert(team4);

		teamDAO.delete(team1);
		
		Session session = HibernateSession.getSession();
		session.beginTransaction();
		
		team4 = (Team) session.get(Team.class, team4.getId());
		if (team4.getRiders().size() > 0) {
			System.out.println("LAZY FALSE");
			for(Rider ride: team4.getRiders()) {
				System.out.println(ride.getName());
			}
		} else {
			System.out.println("LAZY TRUE");
		}
		
		if (team4.getRiders().size() > 0) {
			team4.getRiders().remove(team4.getRiders().size() - 1);
		}
		
		session.getTransaction().commit();
		session.close();
    }
}