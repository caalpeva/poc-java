package team.boolbee.hibernate.xml.onetomany.unidir.model;

import org.hibernate.Session;
import org.testng.annotations.Test;

import team.boolbee.hibernate.xml.onetomany.unidir.dao.RiderDAO;
import team.boolbee.hibernate.xml.onetomany.unidir.dao.TeamDAO;
import team.boolbee.poc.hibernate.utils.HibernateSession;


public class OneToManyBidirXmlTest {
	
    @Test
    public void testOneToOneMappedByXml() {
    	Rider player1 = new Rider();
		player1.setName("Lance Armstrong");
		player1.setNumber(21);
		
		Rider player2 = new Rider();
		player2.setName("Andreas Kloden");
		player2.setNumber(24);
		
		Rider player9 = new Rider();
		player9.setName("Fabian Cancellara");
		player9.setNumber(13);
		
		Team team1 = new Team();
		team1.setName("Team RadioShack");
		team1.addRider(player1);
		team1.addRider(player9);
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
		team3.setName("Saxo Bank");
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

		team3 = teamDAO.selectById(team3.getId());
		player9.setTeam(team3);
		team3.addRider(player9);
		
		RiderDAO riderDAO = new RiderDAO();
		riderDAO.update(player9);
		
		team1 = teamDAO.selectById(team1.getId());
		teamDAO.delete(team1);

		Team team5 = new Team();
		team5.setName("Liquigas");
		
		Rider player10 = new Rider();
		player10.setName("Ivan Basso");
		player10.setNumber(41);
		player10.setTeam(team5);
		team5.addRider(player10);
		
		riderDAO.insert(player10);
		
		//team5 = teamDAO.selectById(team5.getId());

		Session session = HibernateSession.getSession();
		session.beginTransaction();
		
		team5 = (Team) session.get(Team.class, team5.getId());
		
		Rider player11 = new Rider();
		player11.setName("Roman Kreuziger");
		player11.setNumber(44);
		player11.setTeam(team5);
		team5.addRider(player11);
		
		session.persist(player11);
		
		session.getTransaction().commit();
		session.close();
		
//		Rider player11 = new Rider();
//		player11.setName("Roman Kreuziger");
//		player11.setNumber(44);
//		player11.setTeam(team5);
//		team5.addRider(player11);
//		
//		riderDAO.update(player11);
    }
}