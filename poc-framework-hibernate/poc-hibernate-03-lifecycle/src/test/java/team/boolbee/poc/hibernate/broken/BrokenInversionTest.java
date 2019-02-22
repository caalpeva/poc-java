package team.boolbee.poc.hibernate.broken;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.model.broken.Email;
import team.boolbee.poc.hibernate.model.broken.Message;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class BrokenInversionTest {

	@Test()
    public void testBrokenInversionCode() {
        Long emailId;
        Long messageId;

        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Broken");
        Message message = new Message("Broken");

        email.setMessage(message);
        //message.setEmail(email);

        session.save(email);
        session.save(message);

        emailId = email.getId();
        messageId = message.getId();

        tx.commit();
        session.close();

        assertNotNull(email.getMessage());
        assertNull(message.getEmail());

        session = HibernateSession.getSession();
        tx = session.beginTransaction();
        email = (Email) session.get(Email.class, emailId);
        System.out.println(email);
        message = (Message) session.get(Message.class, messageId);
        System.out.println(message);

        tx.commit();
        session.close();

        assertNotNull(email.getMessage());
        assertNull(message.getEmail());
    }
	
    @Test
    public void testProperSimpleInversionCode() {
        Long emailId;
        Long messageId;

        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Proper");
        Message message = new Message("Proper");

        email.setMessage(message);
        message.setEmail(email);

        session.save(email);
        session.save(message);

        emailId = email.getId();
        messageId = message.getId();

        tx.commit();
        session.close();

        assertNotNull(email.getMessage());
        assertNotNull(message.getEmail());

        session = HibernateSession.getSession();
        tx = session.beginTransaction();
        email = (Email) session.get(Email.class, emailId);
        System.out.println(email);
        message = (Message) session.get(Message.class, messageId);
        System.out.println(message);

        tx.commit();
        session.close();

        assertNotNull(email.getMessage());
        assertNotNull(message.getEmail());
    }
}