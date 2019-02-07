package team.boolbee.poc.spring.hibernate.services;

import team.boolbee.poc.spring.hibernate.model.Person;

public class VehicleRegistrationTransactionalServiceImpl extends VehicleRegistrationServiceImpl {
	
	public VehicleRegistrationTransactionalServiceImpl() {
	}

	@Override
	public void register(Person person) {
		super.register(person);
	}

	// public List<Rant> getRecentRants() {
	// return rantDao.getAllRants();
	// }
	//
	// @Transactional(propagation = Propagation.REQUIRED)
	// public void addMotorist(Motorist motorist)
	// throws MotoristAlreadyExistsException {
	// Motorist existingMotorist = rantDao.getMotoristByEmail(motorist
	// .getEmail());
	// if (existingMotorist != null) {
	// throw new MotoristAlreadyExistsException();
	// }
	//
	// MotoristPrivilege privilege = new MotoristPrivilege("MOTORIST");
	// privilege.setMotorist(motorist);
	// motorist.getPrivileges().add(privilege);
	//
	// rantDao.saveMotorist(motorist);
	//
	// for (Vehicle vehicle : motorist.getVehicles()) {
	// Vehicle existingVehicle = rantDao.findVehicleByPlate(vehicle
	// .getState(), vehicle.getPlateNumber());
	//
	// if (existingVehicle == null) {
	// vehicle.setMotorist(motorist);
	// rantDao.saveVehicle(vehicle);
	// } else {
	// motorist.getVehicles().remove(vehicle);
	// motorist.getVehicles().add(existingVehicle);
	// }
	// }
	// }
	//
	// private void checkForOneMillionthMotorist() {
	// if (notificationPublisher != null
	// && rantDao.getMotoristCount() == 1000000) {
	// notificationPublisher.sendNotification(new Notification(
	// "RantService.OneMillionMotorists", this, 0));
	// }
	// }
	//
	// @Transactional(propagation = Propagation.SUPPORTS, isolation =
	// Isolation.READ_UNCOMMITTED, readOnly = true)
	// public List<Rant> getRantsForVehicle(Vehicle requestedVehicle) {
	// Vehicle vehicle = rantDao.findVehicleByPlate(requestedVehicle.getState(),
	// requestedVehicle.getPlateNumber());
	//
	// return vehicle.getRants();
	// }
	//
	// public List<Rant> getRantsForDay(Date day) {
	// return rantDao.getRantsForDay(day);
	// }
	//
	// /**
	// * Sends an e-mail to tell a motorist that they have new rants
	// *
	// * From Listing 12.1
	// */
	// public void sendEmailForVehicle(Vehicle vehicle) {
	// Motorist motorist = vehicle.getMotorist();
	// if (motorist == null) {
	// return;
	// }
	//
	// SimpleMailMessage message = new SimpleMailMessage(mailMessage);
	// message.setTo(motorist.getEmail());
	//
	// String text = message.getText();
	// text = StringUtils.replace(text, "%STATE%", vehicle.getState());
	// text = StringUtils.replace(text, "%PLATE%", vehicle.getPlateNumber());
	// message.setText(text);
	//
	// mailSender.send(message);
	// }
	//
	// /**
	// * Sends a daily e-mail to motorists who have been ranted about.
	// *
	// * From Listing 12.2.
	// */
	// public void sendDailyRantEmails() {
	// List<Rant> rantsForToday = getRantsForDay(new Date());
	//
	// Set<Vehicle> vehiclesRantedAboutToday = new HashSet<Vehicle>();
	//
	// // extract vehicles and place into set (for uniqueness)
	// for (Rant rant : rantsForToday) {
	// vehiclesRantedAboutToday.add(rant.getVehicle());
	// }
	//
	// for (Vehicle vehicle : vehiclesRantedAboutToday) {
	// sendEmailForVehicle(vehicle);
	// }
	// }
	//
	// // injected
	// private RantDao rantDao;
	//
	// public void setRantDao(RantDao rantDao) {
	// this.rantDao = rantDao;
	// }
	//
	// private MailSender mailSender;
	//
	// public void setMailSender(MailSender mailSender) {
	// this.mailSender = mailSender;
	// }
	//
	// private SimpleMailMessage mailMessage;
	//
	// public void setMailMessage(SimpleMailMessage mailMessage) {
	// this.mailMessage = mailMessage;
	// }
	//
	// // injected by JMX implementation
	// private NotificationPublisher notificationPublisher;
	//
	// public void setNotificationPublisher(
	// NotificationPublisher notificationPublisher) {
	// this.notificationPublisher = notificationPublisher;
	// }
}
