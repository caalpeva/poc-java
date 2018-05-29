package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class JCLTest extends TestCase {

	public void testLogging() {
		while(true) {
			new LogRegistrar().register("This is a message");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}