package team.boolbee.poc.spring.hibernate.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.dao.VehicleDao;
import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehicleRegistrationServiceImpl implements VehicleRegistrationService {
	
	private PersonDao personDao;
	private VehicleDao vehicleDao;
	private MailSender mailSender;
	private SimpleMailMessage mailMessage; 
	
	public VehicleRegistrationServiceImpl() {
	}

	public void register(Person person) {
		for(Vehicle vehicle: person.getVehicles()) {
			vehicle.setRegistrationDate(new Date());
		}
		
		personDao.savePerson(person);		
		sendEmailToUser(person);
	}
	
	public List<Person> getPersons() {
		return personDao.list();
	}	

	public Person getPersonById(Integer id) {
		return personDao.getPersonById(id);
	}
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public Collection<Vehicle> getVehiclesForPersons(Integer personId) {
		Person person = personDao.getPersonById(personId);
		return person.getVehicles();
	}

	public void register(Vehicle vehicle) {
		vehicle.setRegistrationDate(new Date());
		Person person = personDao.getPersonById(vehicle.getPerson().getId());
		person.addVehicle(vehicle);
		personDao.updatePerson(person);
	}

	public List<Vehicle> getVehiclesForDay(Date date) {
		return vehicleDao.getVehiclesForDay(date);
	}
	
	private void sendEmailToUser(Person person) {
		if (person == null) {
			return;
		}
		
		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		message.setTo(person.getEmail());
		
		String text = message.getText();
		text = text.replace("%NAME%", person.getName());
		text = text.replace("%SURNAME%", person.getSurname());
		message.setText(text);
		
		mailSender.send(message);
	}
}