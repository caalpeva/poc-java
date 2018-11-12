package team.boolbee.poc.hibernate.utils;

import org.hibernate.Session;
import org.testng.annotations.Test;

public class SessionBuilderTest {

	@Test
	public void testSessionBuilder() {
		Session session = HibernateSession.getSession();
		session.close();
	}
	
}
