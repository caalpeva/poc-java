package team.boolbee.poc.hibernate.mapped;

import team.boolbee.poc.hibernate.model.mapped.Email;
import team.boolbee.poc.hibernate.model.mapped.Message;
import team.boolbee.poc.hibernate.utils.SessionFactoryHelper;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WorkingInversionTest {

    @Test
    public void testImpliedRelationship() {
        Long emailId;
        Long messageId;

        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Inverse Email");
        Message message = new Message("Inverse Message");

        //email.setMessage(message);
        message.setEmail(email);

        session.save(email);
        session.save(message);

        emailId = email.getId();
        messageId = message.getId();

        tx.commit();
        session.close();

        assertEquals(email.getSubject(), "Inverse Email");
        assertEquals(message.getContent(), "Inverse Message");
        assertNull(email.getMessage());
        assertNotNull(message.getEmail());

        session = SessionFactoryHelper.getSession();
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
