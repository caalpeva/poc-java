package team.boolbee.poc.spring.security.dao;

import java.util.List;

import team.boolbee.poc.spring.security.model.Person;

public interface PersonDao {

	public int getPersonCount();
	
	public void savePerson(Person person);
	
	public void updatePerson(Person person);
	
	public Person getPersonById(Integer id);
	
	public List<Person> list();
}