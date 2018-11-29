package team.boolbee.hibernate.xml.onetomany.unidir.dao;

import java.util.List;

import team.boolbee.hibernate.xml.onetomany.unidir.model.Rider;

public interface RiderDAOInterface {
	
	public Rider selectById(Long id);
	public List<Rider> selectAll ();
	public void insert (Rider player);
	public void update (Rider player);
	public void delete (Rider player);

}
