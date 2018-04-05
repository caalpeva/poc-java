package team.boobee.poc.spring.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boobee.poc.spring.hibernate.domain.Motorist;
import team.boobee.poc.spring.hibernate.domain.Rant;
import team.boobee.poc.spring.hibernate.domain.Vehicle;

public class HibernateRantDaoUsingTemplate implements RantDao {
	private static final String MOTORIST = Motorist.class.getName();
	private static final String RANT = Rant.class.getName();
	private static final String VEHICLE = Vehicle.class.getName();

	public HibernateRantDaoUsingTemplate() {
	}

	public void saveVehicle(Vehicle vehicle) {
		hibernateTemplate.saveOrUpdate(vehicle);
	}

	public Vehicle findVehicleByPlate(String state, String plateNumber) {
		List results = hibernateTemplate.find("from " + VEHICLE + " where state = ? and plateNumber = ?",
				new Object[] { state, plateNumber });

		if (results.size() > 0) {
			return (Vehicle) results.get(0);
		}

		return null; // TODO - Should I throw an exception instead?
	}

	public void saveRant(Rant rant) {
		hibernateTemplate.saveOrUpdate(rant);
	}

	public List<Rant> getAllRants() {
		return hibernateTemplate.loadAll(Rant.class);
	}

	public List<Rant> getRantsForDay(Date day) {
		return hibernateTemplate.loadAll(Rant.class);
	}

	public void saveMotorist(Motorist driver) {
		hibernateTemplate.saveOrUpdate(driver);
	}

	public Motorist getDriverById(Integer id) {
		return (Motorist) hibernateTemplate.load(Motorist.class, id);
	}

	public Motorist getMotoristByEmail(String email) {
		List results = hibernateTemplate.find("from " + MOTORIST + " where email = ?", email);

		if (results.size() > 0) {
			return (Motorist) results.get(0);
		}
		return null; // TODO - Should I throw an exception instead?
	}

	public int getMotoristCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	// injected
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}