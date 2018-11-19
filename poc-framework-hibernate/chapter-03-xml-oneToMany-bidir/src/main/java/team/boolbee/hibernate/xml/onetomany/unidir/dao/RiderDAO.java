/**
 * 
 */
package team.boolbee.hibernate.xml.onetomany.unidir.dao;

import java.util.List;

import org.hibernate.Session;

import team.boolbee.hibernate.xml.onetomany.unidir.model.Rider;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class RiderDAO implements RiderDAOInterface {

	public Rider selectById(Long id) {
		Session session = HibernateSession.getSession();

		Rider player = (Rider) session.get(Rider.class, id);

		session.close();
		return player;
	}

	@SuppressWarnings("unchecked")
	public List<Rider> selectAll() {
		Session session = HibernateSession.getSession();

		List<Rider> players = session.createQuery("from Player").list();

		session.close();
		return players;
	}

	public void insert(Rider player) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.persist(player);

		session.getTransaction().commit();
		session.close();
	}

	public void update(Rider player) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.merge(player);

		session.getTransaction().commit();
		session.close();
	}

	public void delete(Rider player) {
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		session.delete(player);

		session.getTransaction().commit();
		session.close();
	}
}