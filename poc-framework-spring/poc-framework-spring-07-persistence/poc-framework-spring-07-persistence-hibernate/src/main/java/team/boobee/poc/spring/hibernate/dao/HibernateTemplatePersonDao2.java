package team.boobee.poc.spring.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import team.boobee.poc.spring.hibernate.model.Person;
import team.boobee.poc.spring.hibernate.model.Rant;
import team.boobee.poc.spring.hibernate.model.Vehicle;

public class HibernateTemplatePersonDao2 implements RantDao {
	private static final String MOTORIST = Person.class.getName();
	private static final String RANT = Rant.class.getName();
	private static final String VEHICLE = Vehicle.class.getName();

	public HibernateTemplatePersonDao2() {
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

	public void saveMotorist(Person driver) {
		hibernateTemplate.saveOrUpdate(driver);
	}

	public Person getDriverById(Integer id) {
		return (Person) hibernateTemplate.load(Person.class, id);
	}

	public Person getMotoristByEmail(String email) {
		List results = hibernateTemplate.find("from " + MOTORIST + " where email = ?", email);

		if (results.size() > 0) {
			return (Person) results.get(0);
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