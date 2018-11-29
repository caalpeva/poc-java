package team.boolbee.hibernate.xml.onetomany.unidir.dao;

import java.util.List;

import team.boolbee.hibernate.xml.onetomany.unidir.model.Team;

/**
 * interface for TeamDAO class
 * @author Eugenia Pérez
 * @email eugenia_perez@cuatrovientos.org
 */
public interface TeamDAOInterface {
	
	public Team selectById(Long id);
	public List<Team> selectAll ();
	public void insert (Team team);
	public void update (Team team);
	public void delete (Team team);

}
