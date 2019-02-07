package team.boolbee.poc.spring.jms;

import java.util.Calendar;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.jms.model.Person;

public class JsmSenderTest extends AbstractDependencyInjectionSpringContextTests {
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-jms.xml", "spring-jms-sender.xml" };
	}
	
	public void testSender() throws InterruptedException {
		Person person = new Person();
		person.setName("Nedwar");
		person.setSurname("Flanders");
		Calendar calendar = Calendar.getInstance();
		calendar.set(1959, 0, 20);
		person.setBirthDate(calendar.getTime());
		person.setEmail("nflanders@thesimpsons.com");
		
		HrDepartmentGateway gateway = (HrDepartmentGateway) getApplicationContext().getBean("hrDepartmentGateway");
		System.out.println("Sending message...");
		System.out.println(person);
		gateway.sendPersonInfo(person);
		
		System.out.println("Doing something else...");
		Thread.sleep(1000);
		
		person = new Person();
		person.setName("Julius");
		person.setSurname("Hibbert");
		calendar = Calendar.getInstance();
		calendar.set(1963, 6, 7);
		person.setBirthDate(calendar.getTime());
		person.setEmail("jhibbert@thesimpsons.com");
		 
		System.out.println("Sending message...");
		System.out.println(person);
		gateway.sendPersonInfo(person);
		
		System.out.println("Doing something else...");
		Thread.sleep(1000);
	}
}