package team.boolbee.poc.logging.jcl;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class CommonsLoggingTest extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String [] { "applicationContext.xml" };
	}
	
	public void testLogging() {
		StartupManager startupManager = (StartupManager) getApplicationContext().getBean("startupManager");
		startupManager.init();
	}
}