package team.boolbee.poc.hibernate.sql.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.sql.model.Product;
import team.boolbee.poc.hibernate.sql.model.Supplier;
import team.boolbee.poc.hibernate.sql.model.Product.Status;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class SqlQueryTest {

	private Session session;

	@BeforeSuite
	public void populateData() {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		Supplier supplier = new Supplier("Logitech, Inc.");
		supplier.getProducts().add(new Product("Optical Wheel Mouse", "Mouse", 5.00, supplier));
		supplier.getProducts().add(new Product("Trackball Mouse", "Mouse", 22.00, Status.INACTIVE, supplier));
		session.save(supplier);

		supplier = new Supplier("Amazon");
		supplier.getProducts().add(new Product("Avast", "Antivirus", 14.95, Status.INACTIVE, supplier));
		supplier.getProducts().add(new Product("Office 2016", "Tools", 19.95, supplier));
		supplier.getProducts().add(new Product("Fifa 2018", "Game", 42.00, supplier));

		session.save(supplier);
		tx.commit();
		session.close();
	}

	@BeforeMethod
	public void openSession() {
		session = HibernateSession.getSession();
	}

	@AfterMethod
	public void closeSession() {
		if (session.isOpen()) {
			session.close();
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testSimpleQuery() {
		Query query = session.createSQLQuery("SELECT * FROM product ORDER BY price")
				.addScalar("id")
				.addScalar("name")
				.addScalar("description");
		List<Object[]> results = query.list();
		assertEquals(results.size(), 5);
		
		assertEquals("Optical Wheel Mouse", (String) results.get(0)[1]);
		assertEquals("Mouse", (String) results.get(0)[2]);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testEntityQuery() {
		Query query = session.createSQLQuery("SELECT * FROM product ORDER BY price")
				.addEntity(Product.class);
		List<Product> results = query.list();
		assertEquals(results.size(), 5);
		
		Product product = results.get(0);
		assertEquals("Optical Wheel Mouse", product.getName());
		assertEquals("Mouse", product.getDescription());
	}

}