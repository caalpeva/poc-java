package team.boolbee.poc.spring.security.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class FixedJavaMailSenderImpl extends JavaMailSenderImpl {

	@Override
	public void setSession(final Session session) {
		super.setSession(Session.getInstance(session.getProperties(),
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(session.getProperty("mail.user"),
								session.getProperty("mail.password"));
					}
          		}
		));
	}
}