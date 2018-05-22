package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class JCLTest extends TestCase {

	public void testLogging() {
		new LogRegistrar().register("This is a message");
	}
}