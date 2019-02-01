package team.boolbee.poc.spring.security.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import team.boolbee.poc.spring.security.services.VehicleRegistrationService;

public class DailyRegisteredVehicleReportJob extends QuartzJobBean {

	private VehicleRegistrationService registrationService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		registrationService.sendDailyRegisteredVehiclesEmailToUser();
	}
	
	public VehicleRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(VehicleRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
}