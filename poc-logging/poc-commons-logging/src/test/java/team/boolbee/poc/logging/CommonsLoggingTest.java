package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class CommonsLoggingTest extends TestCase {

	public void testLogging() {
		new LogRegister().register("This is a message");
	}
}