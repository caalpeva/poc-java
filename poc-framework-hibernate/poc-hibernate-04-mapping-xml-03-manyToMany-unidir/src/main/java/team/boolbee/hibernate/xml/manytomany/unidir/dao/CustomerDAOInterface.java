package team.boolbee.hibernate.xml.manytomany.unidir.dao;

import java.util.List;

import team.boolbee.hibernate.xml.manytomany.unidir.model.Customer;

public interface CustomerDAOInterface {
	
	public Customer selectById(Long id);
	public List<Customer> selectAll ();
	public void insert (Customer customer);
	public void update (Customer customer);
	public void delete (Customer customer);

}