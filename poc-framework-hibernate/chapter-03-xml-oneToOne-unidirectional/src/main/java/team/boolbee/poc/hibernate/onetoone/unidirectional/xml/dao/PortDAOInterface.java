package team.boolbee.poc.hibernate.onetoone.unidirectional.xml.dao;

import java.util.List;

import team.boolbee.poc.hibernate.onetoone.unidirectional.xml.model.Port;

public interface PortDAOInterface {
	
	public Port selectById(Long id);
	public List<Port> selectAll();
	public void insert (Port port);
	public void insert(Port port, Long serviceId);
	public void update (Port port);
	public void delete (Port port);
	
}