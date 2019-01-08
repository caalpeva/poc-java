package team.boolbee.poc.spring.hibernate.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehicleTemplateDao implements VehicleDao {

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void saveVehicle(Vehicle vehicle) {
		hibernateTemplate.save(vehicle);		
	}
	
//	public Vehicle findVehicleByPlate(String state, String plateNumber) {
//		List results = hibernateTemplate.find("from " + VEHICLE + " where state = ? and plateNumber = ?",
//				new Object[] { state, plateNumber });
//
//		if (results.size() > 0) {
//			return (Vehicle) results.get(0);
//		}
//
//		return null; // TODO - Should I throw an exception instead?
//	}
}