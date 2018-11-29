package team.boolbee.poc.hibernate.events.model;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.GenericDAO;

public class EventListenerTest {

    @Test
    public void saveLoadSales() {
    	Salesperson salesperson1 = new Salesperson("Gil Gunderson", 18000f, 0.15f);
    	Salesperson salesperson2 = new Salesperson("Apu Nahasapeemapetilon", 21000f, 0.20f);
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Sale sale1 = null, sale2 = null, sale3 = null, sale4 = null;
    	try {
			sale1 = new Sale(1200f, 0.10f, dateFormat.parse("2012/11/22"));
			sale2 = new Sale(650f, 0.25f, dateFormat.parse("2014/08/14"));
			sale4 = new Sale(700f, 0.20f, dateFormat.parse("2015/02/15"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, 3);
    	sale3 = new Sale(800f, 0.20f, calendar.getTime());
    	
    	salesperson1.addSales(sale1);
    	salesperson1.addSales(sale2);
    	salesperson1.addSales(sale3);
    	salesperson2.addSales(sale4);
    	
    	GenericDAO<Salesperson> salespersonDAO = new GenericDAO<Salesperson>();
    	salespersonDAO.insert(salesperson1);
    	salespersonDAO.insert(salesperson2);

    	salesperson1 = salespersonDAO.selectById(salesperson1.getId(), Salesperson.class);
    	assertEquals(salesperson1.getSales().size(), 3);
    	
    	salesperson2 = salespersonDAO.selectById(salesperson2.getId(), Salesperson.class);
    	assertEquals(salesperson2.getSales().size(), 1);
    	
    	GenericDAO<Sale> saleDAO = new GenericDAO<Sale>();
    	saleDAO.delete(sale1);
    	
    	System.out.println(salespersonDAO.selectById(salesperson1.getId(), Salesperson.class));
    }
}