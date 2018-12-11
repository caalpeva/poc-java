package team.boolbee.poc.hibernate.criteria.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Criteria;
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

import team.boolbee.poc.hibernate.criteria.model.Product.Status;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class CriteriaQueryTest {

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
		Criteria criteria = session.createCriteria(Product.class);
		assertEquals(criteria.list().size(), 5);
	}

	@Test
	public void testUniqueResultQuery() {
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setMaxResults(1);
		assertNotNull((Product) criteria.uniqueResult());
	}

	@Test
	public void testCommonRestrictionQueries() {
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("description", "Mouse"));
		assertEquals(criteria.list().size(), 2);

		criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.ne("description", "Mouse"));
		assertEquals(criteria.list().size(), 3);

		criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.lt("price", 10.0));
		assertEquals(criteria.list().size(), 1);

		criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.gt("price", 10.0));
		assertEquals(criteria.list().size(), 4);

		// should return none, based on case
		criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.like("description", "mou%"));
		assertEquals(criteria.list().size(), 0);

		criteria = session.createCriteria(Product.class).add(Restrictions.like("name", "%Mouse"))
				.add(Restrictions.between("price", 10.0, 25.0));
		assertEquals(criteria.list().size(), 1);
	}

	@Test
	public void testLogicalConditionRestrictionQueries() {
		Criterion mouseCriterion = Restrictions.eq("description", "Mouse");
		Criterion priceLessThanCriterion = Restrictions.lt("price", 15.0);

		// To express OR Conditional
		LogicalExpression orConditional = Restrictions.or(mouseCriterion, priceLessThanCriterion);
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(orConditional);
		assertEquals(criteria.list().size(), 3);

		// To express AND Conditional
		LogicalExpression andConditional = Restrictions.and(mouseCriterion, priceLessThanCriterion);
		criteria = session.createCriteria(Product.class);
		criteria.add(andConditional);
		assertEquals(criteria.list().size(), 1);
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
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.gt("price", 10.0));
		criteria.addOrder(Order.desc("price"));
		List<Product> results = (List<Product>) criteria.list();
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
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setFirstResult(3);
		criteria.setMaxResults(20);
		assertEquals(criteria.list().size(), 2);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testAssociationQueries() {
		Criteria criteria = session.createCriteria(Supplier.class);
		Criteria otherCriteria = criteria.createCriteria("products");
		otherCriteria.add(Restrictions.ge("price", 40.0));
		List<Supplier> suppliers = (List<Supplier>) criteria.list();
		assertEquals(suppliers.size(), 1);

		criteria = session.createCriteria(Product.class);
		otherCriteria = criteria.createCriteria("supplier");
		otherCriteria.add(Restrictions.eq("name", "Amazon"));
		assertEquals(criteria.list().size(), 3);

		criteria = session.createCriteria(Supplier.class)
				.createAlias("products", "p")
				.add(Restrictions.eq("p.status", Status.INACTIVE));
		suppliers = (List<Supplier>) criteria.list();
		assertEquals(suppliers.size(), 2);
	}

	@Test
	public void testQueriesByExample() {
		Product product = new Product();
		product.setPrice(19.95);
		product.setStatus(Status.ACTIVE);

		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Example.create(product));
		assertEquals(criteria.list().size(), 1);

		product.setName("optical wheel mouse");
		Example example = Example.create(product);
		example.excludeZeroes();
		example.excludeProperty("price");

		criteria = session.createCriteria(Product.class);
		criteria.add(example);
		assertEquals(criteria.list().size(), 0);

		example.ignoreCase();
		example.enableLike();

		criteria = session.createCriteria(Product.class);
		criteria.add(example);
		assertEquals(criteria.list().size(), 1);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testProjectionRowCount() {
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(Projections.rowCount());
		List<Long> results = criteria.list();
		assertEquals(results.size(), 1);
		assertEquals(results.get(0).intValue(), 5);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testProjectionAggregation() {
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.min("price"));
		projectionList.add(Projections.max("price"));
		projectionList.add(Projections.avg("price"));
		projectionList.add(Projections.countDistinct("name"));
		projectionList.add(Projections.countDistinct("description"));

		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(projectionList);
		List<Object[]> results = criteria.list();
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
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("status")); // .as("order")
		projectionList.add(Projections.max("price"));

		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.asc("status")); // Order.asc("order")
		List<Object[]> results = criteria.list();
		assertEquals(results.size(), 2);
		for (Object[] p : results) {
			System.out.printf("%-25s $%6.2f%n", p[0].toString(), (Double) p[1]);
		}
	}
}