package chapter04.general;

import chapter04.model.SimpleObject;
import team.boolbee.poc.hibernate.utils.SessionFactoryHelper;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MergeRefreshTest {
    @Test
    public void testMerge() {
        Long id;
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        SimpleObject simpleObject = new SimpleObject();

        simpleObject.setText("testMerge");
        simpleObject.setValue(1L);

        session.save(simpleObject);

        id = simpleObject.getId();

        tx.commit();
        session.close();

        SimpleObject so = validateSimpleObject(id, 1L);

        so.setValue(2L);

        session = SessionFactoryHelper.getSession();
        tx = session.beginTransaction();

        session.merge(so);

        tx.commit();
        session.close();

        validateSimpleObject(id, 2L);
    }

    @Test
    public void testRefresh() {
        Long id;
        Session session = team.boolbee.poc.hibernate.utils.SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        SimpleObject simpleObject = new SimpleObject();

        simpleObject.setText("testMerge");
        simpleObject.setValue(1L);

        session.save(simpleObject);

        id = simpleObject.getId();

        tx.commit();
        session.close();

        SimpleObject so = validateSimpleObject(id, 1L);

        so.setValue(2L);

        session = SessionFactoryHelper.getSession();
        tx = session.beginTransaction();

        session.refresh(so);

        tx.commit();
        session.close();

        validateSimpleObject(id, 1L);
    }

    private SimpleObject validateSimpleObject(Long id, Long value) {
        Session session;
        Transaction tx;// validate the database values
        session = SessionFactoryHelper.getSession();
        tx = session.beginTransaction();

        SimpleObject so = (SimpleObject) session.load(SimpleObject.class, id);

        assertEquals(so.getText(), "testMerge");
        assertEquals(so.getValue(), value);

        tx.commit();
        session.close();

        return so;
    }
}
