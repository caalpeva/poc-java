package team.boolbee.poc.spring.hibernate.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public interface VehicleRegistrationService {
  public void register(Person person);
  public List<Person> getPersons();
  public Person getPersonById(Integer id);
  public Collection<Vehicle> getVehiclesForPersons(Integer personId);
  
  public void register(Vehicle vehicle);
  public List<Vehicle> getVehiclesForDay(Date day);
}