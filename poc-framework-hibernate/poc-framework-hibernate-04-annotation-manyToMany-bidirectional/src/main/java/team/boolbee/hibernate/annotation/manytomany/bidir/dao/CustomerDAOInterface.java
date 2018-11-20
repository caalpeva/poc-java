package team.boolbee.hibernate.annotation.manytomany.bidir.dao;

import java.util.List;

import team.boolbee.hibernate.annotation.manytomany.bidir.model.Customer;

public interface CustomerDAOInterface {
	
	public Customer selectById(Long id);
	public Customer selectByName(String name);
	public List<Customer> selectAll ();
	public void insert (Customer customer);
	public void update (Customer customer);
	public void delete (Customer customer);

}