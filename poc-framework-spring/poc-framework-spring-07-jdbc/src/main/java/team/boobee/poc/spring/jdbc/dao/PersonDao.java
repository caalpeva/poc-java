package team.boobee.poc.spring.jdbc.dao;

import java.util.List;

import team.boobee.poc.spring.jdbc.domain.Person;

public interface PersonDao {

	public void savePerson(Person person);
	
	public void updatePerson(Person person);
	
	public Person getPersonById(Integer id);
	
	public List<Person> list();
}