package team.boolbee.hibernate.xml.manytomany.unidir.dao;

import java.util.List;

import org.hibernate.Session;

import team.boolbee.hibernate.xml.manytomany.unidir.model.BankAccount;
import team.boolbee.poc.hibernate.utils.HibernateSession;

/**
 * implementation of BankAccountDAOInterface
 * @author Eugenia Pérez
 * @email eugenia_perez@cuatrovientos.org
 */
public class BankAccountDAO implements BankAccountDAOInterface {

	/* 
	 * selects one bankAccount by Id
	 * @param id
	 * @return Subject
	 */
	public BankAccount selectById(Long id) {
	    Session session = HibernateSession.getSession();
	 
	    BankAccount bankAccount = (BankAccount) session.get(BankAccount.class, id);
	    
	    session.close();
	    return bankAccount;
	}
	
	public BankAccount selectByNumber(String number) {
	    Session session = HibernateSession.getSession();
	 
	    BankAccount bankAccount = (BankAccount) session.createQuery(
	    		String.format("from BankAccount b where b.number = '%s'", number)).uniqueResult();
	    
	    session.close();
	    return bankAccount;
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> selectAll() {
		Session session = HibernateSession.getSession();
	 
	    List<BankAccount> bankAccounts = session.createQuery("from BankAccount").list();
	    
	    session.close();
	    return bankAccounts;
	}

	/*
	 * inserts a new bankAccount in database
	 * bankAccount must come with the idcar set 
	 * @param new bankAccount
	 */
	public void insert(BankAccount bankAccount) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	 
	    session.persist(bankAccount);    
	    
	    session.getTransaction().commit();	         
	    session.close();

	}

	/*
	 * updates bankAccount
	 * @param bankAccount to update
	 */
	public void update(BankAccount bankAccount) {
		Session session = HibernateSession.getSession();
		    session.beginTransaction();
		 
		    session.merge(bankAccount); 
		    
		    session.getTransaction().commit();		 
		    session.close();
	}

	/*
	 * delete given bankAccount
	 * @param bankAccount to delete
	 */
	public void delete(BankAccount bankAccount) {
		Session session = HibernateSession.getSession();  
	    session.beginTransaction();
	    
	    session.delete(bankAccount);
	 
	    session.getTransaction().commit();
	    session.close();
	}

}