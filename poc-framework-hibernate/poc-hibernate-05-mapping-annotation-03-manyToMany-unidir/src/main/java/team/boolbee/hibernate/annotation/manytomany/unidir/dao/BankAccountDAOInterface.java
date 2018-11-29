package team.boolbee.hibernate.annotation.manytomany.unidir.dao;

import java.util.List;

import team.boolbee.hibernate.annotation.manytomany.unidir.model.BankAccount;

public interface BankAccountDAOInterface {
	
	public BankAccount selectById(Long id);
	public List<BankAccount> selectAll ();
	public void insert (BankAccount bankAccount);
	public void update (BankAccount bankAccount);
	public void delete (BankAccount bankAccount);

}