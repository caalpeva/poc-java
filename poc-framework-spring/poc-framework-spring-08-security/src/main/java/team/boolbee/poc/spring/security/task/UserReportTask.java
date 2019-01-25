package team.boolbee.poc.spring.security.task;

import java.util.TimerTask;

import team.boolbee.poc.spring.hibernate.services.VehicleRegistrationService;

public class UserReportTask extends TimerTask {

	private VehicleRegistrationService registrationService;
	
	@Override
	public void run() {
		registrationService.sendUserListEmailToAdmin();
	}

	public VehicleRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(VehicleRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
}