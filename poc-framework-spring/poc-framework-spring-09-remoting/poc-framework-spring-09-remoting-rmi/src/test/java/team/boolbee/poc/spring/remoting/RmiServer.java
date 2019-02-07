package team.boolbee.poc.spring.remoting;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class RmiServer extends AbstractDependencyInjectionSpringContextTests {
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "rmi-server.xml" };
	}
	
	public void testServer() {
		System.out.println("aaa");
		System.out.println("bbb");
		 //getApplicationContext().getBean("rmiServiceExporter");
	}
}
