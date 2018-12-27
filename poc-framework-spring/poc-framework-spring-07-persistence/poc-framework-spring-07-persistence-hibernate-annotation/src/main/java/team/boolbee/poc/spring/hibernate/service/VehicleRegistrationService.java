package team.boolbee.poc.spring.hibernate.service;

import java.util.List;

import team.boolbee.poc.spring.hibernate.model.Person;

public interface VehicleRegistrationService {
  public void register(Person person);
  public List<Person> getPersons();
  public Person getPersonById(Integer id);
}