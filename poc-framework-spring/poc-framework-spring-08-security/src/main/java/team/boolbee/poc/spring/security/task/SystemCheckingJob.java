package team.boolbee.poc.spring.security.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SystemCheckingJob extends QuartzJobBean {

	private static boolean firstTime = true;
	
	private MailSender mailSender;
	private String administratorEmail;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {
			System.out.println("TimerTask started");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(administratorEmail);
			message.setFrom("no-reply@gmail.com");
			message.setSubject("Application Checking");
			message.setText((firstTime)? "Application is started": "Application is running");
			System.out.println("Sending system checking mail...");
			mailSender.send(message);
			firstTime = false;
		} catch(MailException e) {
			e.printStackTrace();
		}
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getAdministratorEmail() {
		return administratorEmail;
	}

	public void setAdministratorEmail(String administratorEmail) {
		this.administratorEmail = administratorEmail;
	}
}