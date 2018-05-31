package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class SLF4JTest extends TestCase {

	public void testLogging() {
		new SLF4JRegistrar().register("This is a message");
	}
}