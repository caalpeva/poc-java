package team.boolbee.poc.dwr.services;

import junit.framework.TestCase;

public class TrafficServiceTest extends TestCase {

	public void testTrafficInstance() {
		new TrafficServiceImpl().getTrafficInfo("12345", 1, 1);
	}

}