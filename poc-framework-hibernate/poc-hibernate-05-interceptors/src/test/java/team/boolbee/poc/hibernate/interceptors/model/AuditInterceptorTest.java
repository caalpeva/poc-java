package team.boolbee.poc.hibernate.interceptors.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.interceptors.AuditSalesInterceptor;
import team.boolbee.poc.hibernate.interceptors.model.Sale;
import team.boolbee.poc.hibernate.interceptors.model.Salesperson;
import team.boolbee.poc.hibernate.utils.GenericInterceptorDAO;

public class AuditInterceptorTest {
	
    @Test
    public void testInterceptors() {
    	Salesperson salesperson1 = new Salesperson("Gil Gunderson", 18000f, 0.15f);
    	Salesperson salesperson2 = new Salesperson("Apu Nahasapeemapetilon", 21000f, 0.20f);
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Sale sale1 = null, sale2 = null, sale3 = null;
    	try {
			sale1 = new Sale(1200f, 0.10f, dateFormat.parse("2012/11/22"));
			sale2 = new Sale(650f, 0.25f, dateFormat.parse("2014/08/14"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, 3);
    	sale3 = new Sale(800f, 0.20f, calendar.getTime());
    	
    	salesperson1.addSales(sale1);
    	salesperson1.addSales(sale2);
    	salesperson1.addSales(sale3);
    	
    	AuditSalesInterceptor auditSalesInterceptor = new AuditSalesInterceptor();
    	GenericInterceptorDAO<Salesperson> salespersonDAO = new GenericInterceptorDAO<Salesperson>(auditSalesInterceptor);
    	salespersonDAO.insert(salesperson1);
    	salespersonDAO.insert(salesperson2);
    	
    	GenericInterceptorDAO<Sale> saleDAO = new GenericInterceptorDAO<Sale>(auditSalesInterceptor);
//    	saleDAO.insert(sale1);
//    	saleDAO.insert(sale2);
//    	Sale sale4 = null;
//		try {
//			sale4 = new Sale(650f, 0.25f, dateFormat.parse("2014/08/14"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	sale4.setSalesperson(salesperson2);
//    	saleDAO.insert(sale4);
    	
    	saleDAO.delete(sale1);
    	
    	System.out.println(salespersonDAO.selectById(salesperson1.getId(), Salesperson.class));
    }
}