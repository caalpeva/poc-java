package team.boolbee.poc.logging.slf4j;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.logging.jcl.StartupManager;

public class SimpleLoggingFacadeForJavaTest extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String [] { "applicationContext.xml" };
	}
	
	public void testLogging() {
		StartupManager startupManager = (StartupManager) getApplicationContext().getBean("startupManager");
		startupManager.init();
	}
}