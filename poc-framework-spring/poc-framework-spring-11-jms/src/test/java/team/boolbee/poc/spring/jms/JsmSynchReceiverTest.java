package team.boolbee.poc.spring.jms;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.jms.model.Person;

public class JsmSynchReceiverTest extends AbstractDependencyInjectionSpringContextTests {
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-jms.xml", "spring-jms-receiver.xml" };
	}
	
	public void testReceiver() throws InterruptedException {
		HrDepartmentReceiverGateway gateway = (HrDepartmentReceiverGateway) getApplicationContext().getBean("hrDepartmentReceiverGateway");
		while(true) {
			System.out.println("Waiting for message...");
			Person person = gateway.receivePersonInfo();
			if (person != null) {
				System.out.println(person);
			} else {
				break;
			}
		} // while
	}
}