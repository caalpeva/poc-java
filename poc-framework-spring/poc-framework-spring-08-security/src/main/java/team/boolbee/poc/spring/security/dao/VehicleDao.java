package team.boolbee.poc.spring.security.dao;

import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.security.model.Vehicle;

public interface VehicleDao {

	public void saveVehicle(Vehicle vehicle);
	public List<Vehicle> getVehiclesForDay(Date day);
}