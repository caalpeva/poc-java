package team.boolbee.poc.hibernate.criteria.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.HibernateSession;

public class CriteriaTest {

	@BeforeSuite
	public void populateData() {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		Supplier supplier = new Supplier("Logitech, Inc.");
		supplier.getProducts().add(new Product("Optical Wheel Mouse", "Mouse", 5.00, supplier));
		supplier.getProducts().add(new Product("Trackball Mouse", "Mouse", 22.00, supplier));
		session.save(supplier);

		supplier = new Supplier("Amazon");
		supplier.getProducts().add(new Product("Avast", "Antivirus", 14.95, supplier));
		supplier.getProducts().add(new Product("Office 2016", "Tools", 19.95, supplier));
		supplier.getProducts().add(new Product("Fifa 2018", "Game", 42.00, supplier));

		session.save(supplier);
		tx.commit();
		session.close();
	}

	@Test
	public void testSimpleCriteriaQuery() {
		Session session = HibernateSession.getSession();

		Criteria criteria = session.createCriteria(Product.class);
		assertEquals(criteria.list().size(), 5);

		session.close();
	}

	@Test
	public void testRestrictionCriteriaQuery() {
		Session session = HibernateSession.getSession();

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

		// AND Conditional
		criteria = session.createCriteria(Product.class)
				.add(Restrictions.like("name", "%Mouse"))
				.add(Restrictions.between("price", 10.0, 25.0));
		assertEquals(criteria.list().size(), 1);
		
		// OR Conditional
		Criterion mouseCriterion = Restrictions.eq("description", "Mouse");
		Criterion priceLessThanCriterion = Restrictions.lt("price", 15.0);
		LogicalExpression orConditional = Restrictions.or(mouseCriterion, priceLessThanCriterion);
		criteria = session.createCriteria(Product.class);
		criteria.add(orConditional);
		assertEquals(criteria.list().size(), 3);
		
		criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.sqlRestriction("lower({alias}.name) like '%optical%'"));
		assertEquals(criteria.list().size(), 1);
		
		Property price = Property.forName("price");
		criteria = session.createCriteria(Product.class)
				.add(Restrictions.disjunction()
						.add(price.isNull())
						.add(price.ge(19.95)))
				.add(Property.forName("name").in(new String[] {
						"Trackball Mouse", "Fifa 2018"}));
		assertEquals(criteria.list().size(), 2);
		
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testOrderedQuery() {
		Session session = HibernateSession.getSession();

		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.gt("price", 10.0));
		criteria.addOrder(Order.desc("price"));
		List<Product> results = criteria.list();
		assertEquals(results.size(), 4);
		
		double currentPrice = results.get(0).getPrice();
		for(Product product: results) {
			if (product.getPrice() > currentPrice) {
				fail("Product list not ordered in descending order\n" +
                        "Product: " + product + "\nPrevious price: " + currentPrice);
			}
			
			currentPrice = product.getPrice();
		}

		session.close();
	}
}