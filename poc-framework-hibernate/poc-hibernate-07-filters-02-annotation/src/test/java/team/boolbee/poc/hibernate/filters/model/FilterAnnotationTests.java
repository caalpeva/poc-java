package team.boolbee.poc.hibernate.filters.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.GenericDAO;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class FilterAnnotationTests {

	@BeforeSuite
	public void setup() {
		Salesperson salesperson1 = new Salesperson("Gil Gunderson", 18000f, 0.15f, true);
		salesperson1.addAreas("North", "East");
		
		Salesperson salesperson2 = new Salesperson("Apu Nahasapeemapetilon", 21000f, 0.20f, true);
		salesperson2.addAreas("East", "South");
		
		Salesperson salesperson3 = new Salesperson("Jeff Albertson", 1500f, 0.05f, false);
		salesperson3.addAreas("South", "West");
		
		Salesperson salesperson4 = new Salesperson("Moe Szyslak", 25000f, 0.1f, true);
		salesperson4.addAreas("West", "Center");

		salesperson2.addSales(new Sale(1200f, 0.15f, createDate(2012, 11, 22)));
		salesperson2.addSales(new Sale(650f, 0.25f, createDate(2018, 8, 14)));
		salesperson3.addSales(new Sale(800f, 0.10f, createDate(2014, 2, 15)));
		salesperson4.addSales(new Sale(700f, 0.35f, createDate(2015, 5, 5)));

		GenericDAO<Salesperson> salespersonDAO = new GenericDAO<Salesperson>();
		salespersonDAO.insert(salesperson1);
		salespersonDAO.insert(salesperson2);
		salespersonDAO.insert(salesperson3);
		salespersonDAO.insert(salesperson4);
	}

	@Test
	public void testSimpleQuery() {
		Session session = HibernateSession.getSession();
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 4);
		session.close();
	}
	
	@Test
	public void testNoParameterFilter() {
		Session session = HibernateSession.getSession();
		session.enableFilter("nameEndsWithAlbertson");
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 1);
		assertEquals(salespersons.get(0).getName(), "Jeff Albertson");
		session.close();
	}
	
	@Test
	public void testActivesFilter() {
		Session session = HibernateSession.getSession();
		Filter filter = session.enableFilter("byStatus");
		filter.setParameter("status", Boolean.TRUE);
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 3);
		session.close();
	}
	
	@Test
	public void testInactivesFilter() {
		Session session = HibernateSession.getSession();
		Filter filter = session.enableFilter("byStatus");
		filter.setParameter("status", Boolean.FALSE);
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 1);
		session.close();
	}
	
	@Test
	public void testAreaFilter() {
		Session session = HibernateSession.getSession();
		session.enableFilter("byArea").setParameter("area", "West");
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 2);
		showSalesByAgent(salespersons);
		session.enableFilter("byArea").setParameter("area", "North");
		salespersons = query.list();
		assertEquals(salespersons.size(), 1);
		assertEquals(salespersons.get(0).getName(), "Gil Gunderson");
		showSalesByAgent(salespersons);
		session.close();
	}
	
	@Test
	public void testBothFilter() {
		Session session = HibernateSession.getSession();
		session.enableFilter("byArea").setParameter("area", "West");
		session.enableFilter("byStatus").setParameter("status", Boolean.TRUE);
		Query query = session.createQuery("from " + Salesperson.class.getSimpleName());
		List<Salesperson> salespersons = query.list();
		assertEquals(salespersons.size(), 1);
		assertEquals(salespersons.get(0).getName(), "Moe Szyslak");
		session.close();
	}
	
	@Test
	public void testDateFilter() {
		GenericDAO<Salesperson> salespersonDAO = new GenericDAO<Salesperson>();
		List<Salesperson> salespersons = salespersonDAO.selectAll(Salesperson.class);
		assertEquals(salespersons.size(), 4);
		
		showSalesByAgent(salespersons);
		for(Salesperson salesperson: salespersons) {
			if (salesperson.getName().equals("Gil Gunderson")) {
				assertEquals(salesperson.getSales().size(), 0);
			}
			if (salesperson.getName().equals("Apu Nahasapeemapetilon")) {
				assertEquals(salesperson.getSales().size(), 2);
			}
			if (salesperson.getName().equals("Jeff Albertson")) {
				assertEquals(salesperson.getSales().size(), 1);
			}
			if (salesperson.getName().equals("Moe Szyslak")) {
				assertEquals(salesperson.getSales().size(), 1);
			}
		} // for
		
		Date filterDate = createDate(2016, 1, 1);
		Session	session = HibernateSession.getSession();
		Filter filter = session.enableFilter("bySalesDate");
		filter.setParameter("salesDate", filterDate);
		salespersons = session.createQuery("from " + Salesperson.class.getSimpleName()).list();
		assertEquals(salespersons.size(), 4);
		
		showSalesByAgent(salespersons);
		for(Salesperson salesperson: salespersons) {
			if (salesperson.getName().equals("Gil Gunderson")) {
				assertEquals(salesperson.getSales().size(), 0);
			}
			if (salesperson.getName().equals("Apu Nahasapeemapetilon")) {
				assertEquals(salesperson.getSales().size(), 1);
				assertTrue(salesperson.getSales().iterator().next().getSalesDate().getTime() > filterDate.getTime());
			}
			if (salesperson.getName().equals("Jeff Albertson")) {
				assertEquals(salesperson.getSales().size(), 0);
			}
			if (salesperson.getName().equals("Moe Szyslak")) {
				assertEquals(salesperson.getSales().size(), 0);
			}
		} // for
		
		session.close();
	}
	
	private void showSalesByAgent(List<Salesperson> agents) {
		for (Salesperson agent : agents) {
			System.out.println(agent);
		}
	}
	
	private Date createDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, 0, 0, 0);
		return calendar.getTime(); 
	}

}