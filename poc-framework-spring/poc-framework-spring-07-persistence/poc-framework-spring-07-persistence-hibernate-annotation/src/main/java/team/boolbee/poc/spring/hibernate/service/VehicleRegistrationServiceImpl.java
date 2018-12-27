package team.boolbee.poc.spring.hibernate.service;

import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehicleRegistrationServiceImpl implements VehicleRegistrationService {
	
	private PersonDao personDao;
	
	public VehicleRegistrationServiceImpl() {
	}

	public void register(Person person) {
		for(Vehicle vehicule: person.getVehicles()) {
			vehicule.setRegistrationDate(new Date());
		}
		
		personDao.savePerson(person);
	}
	
	public List<Person> getPersons() {
		return personDao.list();
	}	

	public Person getPersonById(Integer id) {
		return personDao.getPersonById(id);
	}
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
}