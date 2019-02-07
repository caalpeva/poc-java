package team.boolbee.poc.spring.security.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boolbee.poc.spring.security.model.Person;
import team.boolbee.poc.spring.security.model.Vehicle;

public class VehicleTemplateDao implements VehicleDao {

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void saveVehicle(Vehicle vehicle) {
		Person person = vehicle.getPerson();
		person.addVehicle(vehicle);
		hibernateTemplate.save(vehicle);		
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehiclesForDay(Date date) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Vehicle.class);
		criteria.add(Restrictions.between("registrationDate", getLowestDayTime(date), getHighestDayTime(date)));
		return hibernateTemplate.findByCriteria(criteria);
	}
	
	public Date getHighestDayTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}
	
	public Date getLowestDayTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
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