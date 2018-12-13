package team.boolbee.poc.hibernate.sql.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Criteria;
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
	public void testSimpleQuery() {
		Query query = session.createQuery("from Product");
		query.setComment("This is only a query for product");
		assertEquals(query.list().size(), 5);
	}

	@Test
	public void testUniqueResultQuery() {
		Query query = session.createQuery("from Product");
		query.setMaxResults(1);
		assertNotNull((Product) query.uniqueResult());
	}

	@Test
	public void testCommonRestrictionQueries() {
		Query query = session.createQuery("from Product p where p.description = :description");
		query.setParameter("description", "Mouse");
		assertEquals(query.list().size(), 2);

		query = session.createQuery("from Product p where p.description != :description");
		query.setParameter("description", "Mouse");
		assertEquals(query.list().size(), 3);

		query = session.createQuery("from Product p where p.price < :price");
		query.setParameter("price", 10.0);
		assertEquals(query.list().size(), 1);
		
		query = session.createQuery("from Product p where p.price >= :price");
		query.setParameter("price", 10.0);
		assertEquals(query.list().size(), 4);
		
		// should return none, based on case
		query = session.createQuery("from Product p where p.description like :description");
		query.setParameter("description", "mou%");
		assertEquals(query.list().size(), 0);
		
		query = session.createQuery("from Product p where p.name like :name and (price between :minPrice and :maxPrice)");
		query.setParameter("name", "%Mouse");
		query.setParameter("minPrice", 10.0);
		query.setParameter("maxPrice", 25.0);
		assertEquals(query.list().size(), 1);
	}

	@Test
	public void testOtherRestrictionQueries() {
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.sqlRestriction("lower({alias}.name) like '%optical%'"));
		assertEquals(criteria.list().size(), 1);

		Property price = Property.forName("price");
		criteria = session.createCriteria(Product.class)
				.add(Restrictions.disjunction().add(price.isNull()).add(price.ge(19.95)))
				.add(Property.forName("name").in(new String[] { "Trackball Mouse", "Fifa 2018" }));
		assertEquals(criteria.list().size(), 2);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testOrderedQuery() {
		Query query = session.createQuery("from Product p where p.price > :price order by p.price desc");
		query.setParameter("price", 10.0);
		List<Product> results = (List<Product>) query.list();
		assertEquals(results.size(), 4);

		double currentPrice = results.get(0).getPrice();
		for (Product product : results) {
			if (product.getPrice() > currentPrice) {
				fail("Product list not ordered in descending order\n" + "Product: " + product + "\nPrevious price: "
						+ currentPrice);
			}
			currentPrice = product.getPrice();
		}
	}

	@Test
	public void testPagedQuery() {
		Query query = session.createQuery("from Product");
		query.setFirstResult(3);
		query.setMaxResults(20);
		assertEquals(query.list().size(), 2);
	}

	@Test
	public void testAssociationQueries() {
		Query query = session.createQuery("from Supplier s inner join s.products p where p.price >= :price");
		query.setParameter("price", 40.0);
		assertEquals(query.list().size(), 1);

		query = session.createQuery("from Product p inner join p.supplier s where s.name = :name");
		query.setParameter("name", "Amazon");
		assertEquals(query.list().size(), 3);

		query = session.createQuery("from Product p where p.supplier.name = :name");
		query.setParameter("name", "Amazon");
		assertEquals(query.list().size(), 3);
		
		query = session.createQuery("from Supplier s inner join s.products p where p.status = :status");
		query.setParameter("status", Status.INACTIVE);
		assertEquals(query.list().size(), 2);
	}
	
	@Test
	public void testSubquery() {
		Query query = session.createQuery("select distinct(s.name) from Supplier s inner join s.products p where p in (select p from Product p where p.description like :description)");
		query.setParameter("description", "M%");
		assertEquals(query.list().size(), 1);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testProjectionRowCount() {
		Query query = session.createQuery("select COUNT(*) from Product p");
		List<Long> results = query.list();
		assertEquals(results.size(), 1);
		assertEquals(results.get(0).intValue(), 5);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testProjectionAggregation() {
		Query query = session.createQuery("select MIN(p.price), MAX(p.price), AVG(p.price), COUNT(p.name), COUNT(DISTINCT p.description) from Product p");
		List<Object[]> results = query.list();
		assertEquals(results.size(), 1);
		assertEquals(5.0, (Double) results.get(0)[0]);
		assertEquals(42.0, (Double) results.get(0)[1]);
		assertEquals(20.78, (Double) results.get(0)[2]);
		assertEquals(5, ((Long) results.get(0)[3]).longValue());
		assertEquals(4, ((Long) results.get(0)[4]).longValue());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testProjectionGroupBy() {
		Query query = session.createQuery("select p.status, MAX(p.price) from Product p group by p.status order by p.status asc");
		List<Object[]> results = query.list();
		assertEquals(results.size(), 2);
		for (Object[] p : results) {
			System.out.printf("%-25s $%6.2f%n", p[0].toString(), (Double) p[1]);
		}
	}

	@Test
	public void testPositionalParameters() {
		Query query = session.createQuery("from Product p inner join p.supplier s where s.name = ?0 and p.status = ?1");
		query.setParameter("0", "Amazon");
		query.setParameter("1", Status.ACTIVE);
		assertEquals(query.list().size(), 2);
	}
}