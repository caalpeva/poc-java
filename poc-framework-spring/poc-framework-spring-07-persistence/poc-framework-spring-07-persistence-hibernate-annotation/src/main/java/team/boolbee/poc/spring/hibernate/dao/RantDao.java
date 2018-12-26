package team.boolbee.poc.spring.hibernate.dao;

import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Rant;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public interface RantDao {
  //@CacheFlush(modelId="rantzCacheModel")
  public void saveRant(Rant rant);
  
  //@Cacheable(modelId="rantzCacheModel")
  public List<Rant> getAllRants();

  //@Cacheable(modelId="rantzCacheModel")
  public List<Rant> getRantsForDay(Date day);
  
  public Vehicle findVehicleByPlate(String state, String plateNumber);
  public void saveVehicle(Vehicle vehicle);
  
  public Person getMotoristByEmail(String email);
  public void saveMotorist(Person driver);
  
  public int getMotoristCount();
}
