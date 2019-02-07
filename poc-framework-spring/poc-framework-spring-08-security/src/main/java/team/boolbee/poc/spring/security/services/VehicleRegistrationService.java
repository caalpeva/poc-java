package team.boolbee.poc.spring.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;

@ManagedResource(objectName="rantz:name=VehicleRegistrationService")
public interface VehicleRegistrationService {
	
	public void register(Person person);

	public List<Person> getPersons();

	public Person getPersonById(Integer id);

	public Collection<Vehicle> getVehiclesForPersons(Integer personId);

	public void register(Vehicle vehicle);

	public List<Vehicle> getVehiclesForDay(Date day);

	public void sendUserListEmailToAdmin();
	
	@ManagedOperation(description="Send the daily rant e-mail")
	public void sendDailyRegisteredVehiclesEmailToUser();
}