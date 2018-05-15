package team.boobee.poc.spring.hibernate.dao;

import java.util.Date;
import java.util.List;

import team.boobee.poc.spring.hibernate.domain.Motorist;
import team.boobee.poc.spring.hibernate.domain.Rant;
import team.boobee.poc.spring.hibernate.domain.Vehicle;

public interface RantDao {
  //@CacheFlush(modelId="rantzCacheModel")
  public void saveRant(Rant rant);
  
  //@Cacheable(modelId="rantzCacheModel")
  public List<Rant> getAllRants();

  //@Cacheable(modelId="rantzCacheModel")
  public List<Rant> getRantsForDay(Date day);
  
  public Vehicle findVehicleByPlate(String state, String plateNumber);
  public void saveVehicle(Vehicle vehicle);
  
  public Motorist getMotoristByEmail(String email);
  public void saveMotorist(Motorist driver);
  
  public int getMotoristCount();
}
