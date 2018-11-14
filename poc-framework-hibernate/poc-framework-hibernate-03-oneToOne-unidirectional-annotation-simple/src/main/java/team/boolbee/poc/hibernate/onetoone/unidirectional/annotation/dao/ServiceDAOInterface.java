package team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.dao;

import java.util.List;

import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.model.Service;

public interface ServiceDAOInterface {
	
	public Service selectById(long id);
	public List<Service> selectAll ();
	public void insert (Service president);
	public void update (Service president);
	public void delete (Service president);

}