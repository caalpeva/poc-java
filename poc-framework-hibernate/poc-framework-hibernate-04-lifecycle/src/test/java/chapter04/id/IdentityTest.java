package chapter04.id;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.id.IdentifierGenerationException;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.SessionFactoryHelper;

public class IdentityTest {
    @Test
    public void testAutoIdentity() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        GeneratedAutoIdentity obj = new GeneratedAutoIdentity();

        session.persist(obj);

        tx.commit();
        session.close();

        System.out.println(obj.getId());
    }

    @Test(expectedExceptions = IdentifierGenerationException.class)
    public void testNongeneratedIdentityFailure() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        NongeneratedIdentity obj = new NongeneratedIdentity();

        session.persist(obj);

        tx.commit();
        session.close();

        System.out.println(obj.getId());
    }

    @Test
    public void testNongeneratedIdentity() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        NongeneratedIdentity obj = new NongeneratedIdentity();
        obj.setId(1l);
        session.persist(obj);

        tx.commit();
        session.close();

        System.out.println(obj.getId());
    }

//    @Test
//    public void testSequenceIdentity() {
//        Session session = SessionFactoryHelper.getSession();
//        Transaction tx = session.beginTransaction();
//
//        GeneratedSequenceIdentity obj = new GeneratedSequenceIdentity();
//        session.persist(obj);
//
//        tx.commit();
//        session.close();
//
//        System.out.println(obj.getId());
//    }

    @Test
    public void testTableIdentity() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        GeneratedTableIdentity obj = new GeneratedTableIdentity();
        session.persist(obj);

        tx.commit();
        session.close();

        System.out.println(obj.getId());
    }

    @Test
    public void testIdentityIdentity() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        GeneratedIdentityIdentity obj = new GeneratedIdentityIdentity();
        session.persist(obj);

        tx.commit();
        session.close();

        System.out.println(obj.getId());
    }
}
