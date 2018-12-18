package team.boolbee.hibernate.annotation.manytomany.unidir.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.testng.annotations.Test;

import team.boolbee.hibernate.annotation.manytomany.unidir.dao.CustomerDAO;
import team.boolbee.hibernate.annotation.manytomany.unidir.model.BankAccount;
import team.boolbee.hibernate.annotation.manytomany.unidir.model.Customer;
import team.boolbee.poc.hibernate.utils.HibernateSession;


public class ManyToManyUnidirAnnotationTest {
	
	private String sharedCustom5Account = "4100-4400-4404-4404-0001";
	
    @Test
    public void testManyToManyMappedByAnnotation() {
    	populateBankData();
    	
    	CustomerDAO customerDAO = new CustomerDAO();
    	Customer customer1 = customerDAO.selectById(1L);
    	assertEquals(customer1.getBankAccounts().size(), 2);
    	
    	Customer customer2 = customerDAO.selectById(2L);
    	assertEquals(customer2.getBankAccounts().size(), 2);
    	
    	Customer customer3 = customerDAO.selectById(3L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    	
    	Customer customer4 = customerDAO.selectById(4L);
    	assertEquals(customer4.getBankAccounts().size(), 2);
    	
    	Customer customer5 = customerDAO.selectById(5L);
    	assertEquals(customer5.getBankAccounts().size(), 2);
    	
    	Customer customer6 = customerDAO.selectById(6L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    	
    	Customer customer7 = customerDAO.selectById(7L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    	
    	Session session = HibernateSession.getSession();
    	session.beginTransaction();
    	
		//Se borran algunos clientes y sus cuentas asociadas
		customer2 = (Customer) session.get(Customer.class, customer2.getId());
		session.delete(customer2);
		
		customer5 = (Customer) session.get(Customer.class, customer5.getId());
		BankAccount account = null;
		for(Iterator<BankAccount> it = customer5.getBankAccounts().iterator(); it.hasNext(); ) { 
			account = (BankAccount) it.next();
			if (sharedCustom5Account.equals(account.getNumber())) {
				break;
			}
		}
		
		if (account != null) {
			customer5.getBankAccounts().remove(account);
		}

		session.delete(customer5);
		
		session.getTransaction().commit();
		session.close();
		
    	customer1 = customerDAO.selectById(1L);
    	assertEquals(customer1.getBankAccounts().size(), 2);
    	
    	customer2 = customerDAO.selectById(2L);
    	assertNull(customer2);
    	
    	customer3 = customerDAO.selectById(3L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    	
    	customer4 = customerDAO.selectById(4L);
    	assertEquals(customer4.getBankAccounts().size(), 2);
    	
    	customer5 = customerDAO.selectById(5L);
    	assertNull(customer5);
    	
    	customer6 = customerDAO.selectById(6L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    	
    	customer7 = customerDAO.selectById(7L);
    	assertEquals(customer3.getBankAccounts().size(), 1);
    }
    
    private void populateBankData() {
		Map<String, Float> accountData = new HashMap<String, Float>();
		accountData.put("8100-8800-8808-8808-0001", 10000000.00f);
		accountData.put("8100-8800-8808-8808-0002", 10000000.00f);
		Customer customer1 = createCustomerWithAccounts("Bill Gates", "Medina, Washington, Estados Unidos", accountData);
		
		accountData = new HashMap<String, Float>();
		accountData.put("7100-7700-7707-7707-0001", 8000000.00f);
		accountData.put("7100-7700-7707-7707-0002", 8000000.00f);
		Customer customer2 = createCustomerWithAccounts("Warren Buffet", "Omaha, Nebraska, Estados Unidos", accountData);
		
		accountData = new HashMap<String, Float>();
		accountData.put("6100-6600-6606-6606-0001", 9000000.00f);
		Customer customer3 = createCustomerWithAccounts("Jeff Bezos", "Washington, Estados Unidos", accountData);
		
		String sharedCustom4Account = "2100-2219-2202-2202-0001";
		accountData = new HashMap<String, Float>();
		accountData.put(sharedCustom4Account, 4000000.00f);
		accountData.put("2100-2219-2202-2202-0002", 4000000.00f);
		Customer customer4 = createCustomerWithAccounts("Amancio Ortega", "La coru�a, Espa�a", accountData);
		
		accountData = new HashMap<String, Float>();
		accountData.put(sharedCustom5Account, 3000000.00f);
		accountData.put("4100-4400-4404-4404-0002", 3000000.00f);
		Customer customer5 = createCustomerWithAccounts("Mark Zuckerberg", "Palo Alto, California, Estados Unidos", accountData);
		
		//Se insertan los clientes, con sus cuentas en cascada
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.insert(customer1);
		customerDAO.insert(customer2);
		customerDAO.insert(customer3);
		customerDAO.insert(customer4);
		customerDAO.insert(customer5);

		// Se a�aden clientes con cuentas ya existentes
		Session session = HibernateSession.getSession();
		session.beginTransaction();
		
		BankAccount bankAccount = (BankAccount) session.createQuery(String.format(
				"from BankAccount b where b.number = '%s'", sharedCustom4Account)).uniqueResult();
		
		Customer customer41 = new Customer();
		customer41.setName("Flora Perez");
		customer41.setAddress("La coru�a, Espa�a");
		customer41.addAccount(bankAccount);
		session.persist(customer41);
		
		bankAccount = (BankAccount) session.createQuery(String.format(
				"from BankAccount b where b.number = '%s'", sharedCustom5Account)).uniqueResult();
		Customer customer51 = new Customer();
		customer51.setName("Priscilla Chan");
		customer51.setAddress("Palo Alto, California, Estados Unidos");
		customer51.addAccount(bankAccount);
		session.persist(customer51);

		session.getTransaction().commit();
		session.close();
    }
    
    private Customer createCustomerWithAccounts(String name, String address, Map<String, Float> accountData) {
    	Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
		for(String accountNumber: accountData.keySet()) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setNumber(accountNumber);
			bankAccount.setDeposit(accountData.get(accountNumber));
			bankAccounts.add(bankAccount);
		}
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setBankAccounts(bankAccounts);
		
		return customer;
	}
}