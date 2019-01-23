package team.boolbee.poc.spring.jms;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class JsmAsynchReceiverTest extends AbstractDependencyInjectionSpringContextTests {
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-jms.xml", "spring-jms-receiver-async.xml", "spring-service.xml" };
	}
	
	public void testReceiver() throws InterruptedException {
		while(true) {
			System.out.println("Waiting for message...");
			Thread.sleep(10000);
		} // while
	}
}