/**
 * 
 */
package team.boolbee.hibernate.xml.onetomany.unidir.dao;

import java.util.List;

import org.hibernate.Session;

import team.boolbee.hibernate.xml.onetomany.unidir.model.Team;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class TeamDAO implements TeamDAOInterface {

	/* 
	 * selects one team by Id
	 * @param id
	 * @return Team
	 */
	public Team selectById(Long id) {
		Session session = HibernateSession.getSession();
	 
	    Team team = (Team) session.get(Team.class, id);
	    
	    session.close();
	    return team;
	}

	/*
	 * retrieves all teams from db
	 * @return List of teams
	 */
	public List<Team> selectAll() {
		Session session = HibernateSession.getSession();	 
	    List<Team> teams = session.createQuery("from Team").list();
	    
	    session.close();
	    return teams;
	}

	/*
	 * inserts a new team in database
	 * team must come with the idcar set 
	 * @param new team
	 */
	public void insert(Team team) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();
	 
	    session.persist(team);    
	    
	    session.getTransaction().commit();	         
	    session.close();

	}

	/*
	 * updates team
	 * @param team to update
	 */
	public void update(Team team) {
		Session session = HibernateSession.getSession(); 
		    session.beginTransaction();
		 
		    session.merge(team); 
		    
		    session.getTransaction().commit();		 
		    session.close();
	}

	/*
	 * delete given team
	 * @param team to delete
	 */
	public void delete(Team team) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	    
	    session.delete(team);
	 
	    session.getTransaction().commit();
	    session.close();
	}

}