package team.boolbee.poc.logging.jul;


import java.io.IOException;

import junit.framework.TestCase;

public class Sdk14LoggingTest extends TestCase {

	public void testLogging() {
		try {
			Island.execute();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}