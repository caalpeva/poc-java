package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class SLF4JLogTest extends TestCase {

	public void testLogging() {
		new SLF4JRegister().register("This is a message");
	}
}