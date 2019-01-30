package team.boolbee.poc.dwr.services;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

import junit.framework.TestCase;

import team.boolbee.poc.dwr.model.TrafficInfo;

public class TrafficServiceTest extends TestCase {

	public void testTrafficInstance() {
		new TrafficServiceImpl().getTrafficInfo("12345", 1, 1);
	}

}