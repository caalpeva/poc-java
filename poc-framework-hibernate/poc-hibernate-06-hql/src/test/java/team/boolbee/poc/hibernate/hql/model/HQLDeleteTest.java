package team.boolbee.poc.hibernate.hql.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.HibernateSession;

public class HQLDeleteTest {
	
	@Test
	@SuppressWarnings("unchecked")
    public void testDeleteHQL() {
        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from Product").executeUpdate();
        session.createQuery("delete from Supplier").executeUpdate();
        
        // create a set of suppliers, then delete them
        Supplier supplier=new Supplier("IBM Corporation");
        session.save(supplier);
        supplier.getProducts().add(new Product("IBM 5150", "Computer", 500.00, supplier));

        session.save(new Supplier("Intel Corporation"));
        tx.commit();

        tx = session.beginTransaction();
        Query queryAll = session.createQuery("from Supplier");
        queryAll.setReadOnly(true);
        List<Supplier> suppliers = queryAll.list();
        for(Supplier s:suppliers) {
            System.out.println(s);
        }
        
        assertEquals(suppliers.size(), 2);

        session.createQuery("delete from Product").executeUpdate();
        Query deleteAll = session.createQuery("delete from Supplier");
        deleteAll.executeUpdate();
        
        tx.commit();
        
        assertEquals(queryAll.list().size(), 0);
        
        session.close();
    }
}