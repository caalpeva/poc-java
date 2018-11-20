package team.boolbee.hibernate.annotation.manytomany.bidir.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.testng.annotations.Test;

import team.boolbee.hibernate.annotation.manytomany.bidir.dao.BankAccountDAO;
import team.boolbee.hibernate.annotation.manytomany.bidir.dao.CustomerDAO;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class BidirectionalManyToManyAsociationMappedByAnnotationTest {

	@Test
	public void populateBankData() {
		populateBankDataFromCustomerDAO();

		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.selectByName("Bill Gates");
		assertEquals(customer.getBankAccounts().size(), 2);

		customer = customerDAO.selectByName("Warren Buffet");
		assertEquals(customer.getBankAccounts().size(), 2);

		customer = customerDAO.selectByName("Jeff Bezos");
		assertEquals(customer.getBankAccounts().size(), 2);

		populateCustomerDataFromBankAccountDAO();

		customer = customerDAO.selectByName("Amancio Ortega");
		assertEquals(customer.getBankAccounts().size(), 1);
		
		customer = customerDAO.selectByName("Flora Pérez");
		assertEquals(customer.getBankAccounts().size(), 1);

		customer = customerDAO.selectByName("Mark Zuckerberg");
		assertEquals(customer.getBankAccounts().size(), 1);

		customer = customerDAO.selectByName("Priscilla Chan");
		assertEquals(customer.getBankAccounts().size(), 1);

		BankAccountDAO bankAccountDAO = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDAO.selectByNumber("8100-8800-8808-8808-0001");
		assertEquals(bankAccount.getCustomers().size(), 1);

		bankAccount = bankAccountDAO.selectByNumber("2100-2200-2202-2202-0001");
		assertEquals(bankAccount.getCustomers().size(), 2);

		bankAccount = bankAccountDAO.selectByNumber("4100-4400-4404-4404-0001");
		assertEquals(bankAccount.getCustomers().size(), 2);
	}

	@Test(dependsOnMethods = "populateBankData")
	public void addAccountToExistingCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.selectByName("Priscilla Chan");
		assertEquals(customer.getBankAccounts().size(), 1);

		BankAccount bankAccount = new BankAccount("4100-4400-4404-4404-0002", 4000000f);
		customer.addAccount(bankAccount);

		customerDAO.update(customer);

		customer = customerDAO.selectByName("Priscilla Chan");
		assertEquals(customer.getBankAccounts().size(), 2);
		
//		for (Customer customer : bankAccount.getCustomers()) {
//			Session otherSession = HibernateSession.getSession();
//			otherSession.beginTransaction();
//
//			Customer sameCustomer = (Customer) otherSession.get(Customer.class, customer.getId());
//
//			BankAccount bankAccount2 = new BankAccount();
//			bankAccount2.setNumber("OTRA");
//			bankAccount2.setDeposit(0f);
//			bankAccount2.addCustomer(sameCustomer);
//			otherSession.persist(bankAccount2);
//
//			otherSession.getTransaction().commit();
//			otherSession.close();
//
//			break;
//		}
	}
	
	@Test(dependsOnMethods = "populateBankData")
	public void addCustomerToExistingBankAccount() {
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.selectByName("Jeff Bezos");
		assertEquals(customer.getBankAccounts().size(), 2);

		Customer otherCustomer = customerDAO.selectByName("Mackenzie Bezos");
		assertNull(otherCustomer);

		otherCustomer = new Customer();
		otherCustomer.setName("Mackenzie Bezos");
		otherCustomer.setAddress("Washington, Estados Unidos");
		
		Session session = HibernateSession.getSession();
		session.beginTransaction();

		Query query = session.createQuery("from BankAccount b where b.number=:number");
		query.setParameter("number", customer.getBankAccounts().iterator().next().getNumber());
		BankAccount bankAccount = (BankAccount) query.uniqueResult();

		otherCustomer.addAccount(bankAccount);
		session.persist(otherCustomer);

		session.getTransaction().commit();
		session.close();

		otherCustomer = customerDAO.selectByName("Mackenzie Bezos");
		assertEquals(otherCustomer.getBankAccounts().size(), 1);
	}

	@Test(dependsOnMethods = "populateBankData")
	public void deleteCustomer() {
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.selectByName("Warren Buffet");
		assertEquals(customer.getBankAccounts().size(), 2);
		Set<BankAccount> oldAccounts = new HashSet<BankAccount>(customer.getBankAccounts());
		
		// Se borran el cliente y sus cuentas asociadas
		customerDAO.delete(customer);

		customer = customerDAO.selectByName("Warren Buffet");
		assertNull(customer);

		BankAccountDAO bankAccountDAO = new BankAccountDAO();
		for (BankAccount account: oldAccounts) {
			assertNull(bankAccountDAO.selectByNumber(account.getNumber()));
		}
	}

	@Test(dependsOnMethods = "addCustomerToExistingBankAccount")
	public void deleteCustomerWithSharedBankAccount() {
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.selectByName("Mackenzie Bezos");
		assertEquals(customer.getBankAccounts().size(), 1);

		Customer otherCustomer = customerDAO.selectByName("Jeff Bezos");
		assertEquals(otherCustomer.getBankAccounts().size(), 2);		
		
		BankAccount sharedBankAccount = getSharedBankAccount(otherCustomer);
		assertEquals(sharedBankAccount.getCustomers().size(), 2);

		// Se elimina la asociación del cliente con su cuenta compartida
		otherCustomer.getBankAccounts().remove(sharedBankAccount);
		customerDAO.update(otherCustomer);
		
		BankAccountDAO bankAccountDAO = new BankAccountDAO();
		sharedBankAccount = bankAccountDAO.selectByNumber(sharedBankAccount.getNumber());
		assertEquals(sharedBankAccount.getCustomers().size(), 1);
		
		customer = customerDAO.selectByName("Mackenzie Bezos");
		assertEquals(customer.getBankAccounts().size(), 1);
		
		otherCustomer = customerDAO.selectByName("Jeff Bezos");
		assertEquals(otherCustomer.getBankAccounts().size(), 1);
		
		// Se elimina el cliente con el resto de cuentas asociadas
		customerDAO.delete(otherCustomer);

		otherCustomer = customerDAO.selectByName("Jeff Bezos");
		assertNull(otherCustomer);
	}

	private void populateBankDataFromCustomerDAO() {
		Set<BankAccount> accounts = new HashSet<BankAccount>();
		accounts.add(new BankAccount("8100-8800-8808-8808-0001", 8000000f));
		accounts.add(new BankAccount("8100-8800-8808-8808-0002", 8000000f));
		Customer customer1 = new Customer("Bill Gates", "Washington, Estados Unidos", accounts);

		accounts = new HashSet<BankAccount>();
		accounts.add(new BankAccount("6100-6600-6606-6606-0001", 6000000f));
		accounts.add(new BankAccount("6100-6600-6606-6606-0002", 6000000f));
		Customer customer2 = new Customer("Warren Buffet", "Nebraska, Estados Unidos", accounts);

		accounts = new HashSet<BankAccount>();
		accounts.add(new BankAccount("5100-5500-5505-5505-0001", 5000000f));
		accounts.add(new BankAccount("5100-5500-5505-5505-0002", 5000000f));
		Customer customer3 = new Customer("Jeff Bezos", "Washington, Estados Unidos", accounts);

		// Se insertan los clientes, con sus cuentas en cascada
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.insert(customer1);
		customerDAO.insert(customer2);
		customerDAO.insert(customer3);
	}

	private void populateCustomerDataFromBankAccountDAO() {
		BankAccount bankAccount1 = new BankAccount("2100-2200-2202-2202-0001", 2000000f);
		Set<Customer> customers = new HashSet<Customer>();
		customers.add(new Customer("Amancio Ortega", "La coruña, España", new HashSet<BankAccount>()));
		customers.add(new Customer("Flora Pérez", "La coruña, España", new HashSet<BankAccount>()));
		addCustomersToBankAccount(bankAccount1, customers);

		BankAccount bankAccount2 = new BankAccount("4100-4400-4404-4404-0001", 4000000f);
		customers = new HashSet<Customer>();
		customers.add(new Customer("Mark Zuckerberg", "California, Estados Unidos", new HashSet<BankAccount>()));
		customers.add(new Customer("Priscilla Chan", "California, Estados Unidos", new HashSet<BankAccount>()));
		addCustomersToBankAccount(bankAccount2, customers);

		// Se insertan las cuentas con sus clientes en cascada
		BankAccountDAO bankAccountDAO = new BankAccountDAO();
		bankAccountDAO.insert(bankAccount1);
		bankAccountDAO.insert(bankAccount2);
	}

	private BankAccount addCustomersToBankAccount(BankAccount bankAccount, Set<Customer> customers) {
		for (Customer customer : customers) {
			bankAccount.addCustomer(customer);
		}

		return bankAccount;
	}
	
	//TODO: Utilizar Criteria
	private BankAccount getSharedBankAccount(Customer customer) {
		if (customer.getBankAccounts() != null && customer.getBankAccounts().size() > 0) {
			for(BankAccount account: customer.getBankAccounts()) {
				if (account.getCustomers() != null && account.getCustomers().size() > 1) {
					return account;
				}
			}
		}
		
		return null;
	}
}