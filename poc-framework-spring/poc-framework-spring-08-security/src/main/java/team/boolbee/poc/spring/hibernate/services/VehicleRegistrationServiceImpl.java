package team.boolbee.poc.spring.hibernate.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.Notification;

import java.util.HashSet;

import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import team.boolbee.poc.spring.hibernate.dao.PersonDao;
import team.boolbee.poc.spring.hibernate.dao.VehicleDao;
import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehicleRegistrationServiceImpl implements VehicleRegistrationService, NotificationPublisherAware {

	private PersonDao personDao;
	private VehicleDao vehicleDao;
	private MailSender mailSender;
	private SimpleMailMessage mailMessage;
	private String administratorEmail;

	private NotificationPublisher notificationPublisher;

	public VehicleRegistrationServiceImpl() {
	}

	public void register(Person person) {
		for (Vehicle vehicle : person.getVehicles()) {
			vehicle.setRegistrationDate(new Date());
		}

		personDao.savePerson(person);
		sendRegistrationEmailToUser(person);
		
		if (notificationPublisher != null) {
			notificationPublisher.sendNotification(new Notification("VehicleRegistrationService.NewUser", this, 0));
		}
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

	public String getAdministratorEmail() {
		return administratorEmail;
	}

	public void setAdministratorEmail(String administratorEmail) {
		this.administratorEmail = administratorEmail;
	}

	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
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

	public void sendUserListEmailToAdmin() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(administratorEmail);
		message.setFrom("no-reply@gmail.com");
		message.setSubject("User list");

		StringBuffer text = new StringBuffer();
		List<Person> persons = getPersons();
		for (Person person : persons) {
			text.append(String.format("%s %s %s\n", person.getName(), person.getSurname(), person.getEmail()));
		} // for
		message.setText(text.toString());

		mailSender.send(message);
	}

	public void sendDailyRegisteredVehiclesEmailToUser() {
		List<Vehicle> vehicles = getVehiclesForDay(new Date());

		Set<Person> persons = new HashSet<Person>();
		if (vehicles != null && vehicles.size() > 0) {
			for (Vehicle vehicle : vehicles) {
				persons.add(vehicle.getPerson());
			} // for
		}

		if (persons != null && persons.size() > 0) {
			for (Person person : persons) {
				sendRegistrationEmailToUser(person);
			} // for
		}
	}

	private void sendRegistrationEmailToUser(Person person) {
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