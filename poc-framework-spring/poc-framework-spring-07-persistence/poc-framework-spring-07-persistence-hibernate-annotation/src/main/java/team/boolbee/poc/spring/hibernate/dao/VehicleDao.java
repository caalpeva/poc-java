package team.boolbee.poc.spring.hibernate.dao;

import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.hibernate.model.Vehicle;

public interface VehicleDao {
	
	public void saveVehicle(Vehicle vehicle);

	public List<Vehicle> getAllVehicles();

	public List<Vehicle> getVehiclesForDay(Date day);

	public Vehicle findVehicleByPlate(String state, String plateNumber);

	//public Person getOwnerByEmail(String email);
}