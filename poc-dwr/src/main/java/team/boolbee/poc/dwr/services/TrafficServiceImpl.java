package team.boolbee.poc.dwr.services;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

import team.boolbee.poc.dwr.model.TrafficInfo;

public class TrafficServiceImpl implements TrafficService {

	private static final String ROOT_TRAFFIC_URL = "http://local.yahooapis.com/MapsService/rss/trafficData.xml?";
	
	private String appId = "6oQAvJ78";
	
	public TrafficServiceImpl() {}
	
	public TrafficInfo[] getTrafficInfo(String zipcode, int zoom, int severity) {
		try {
			URL feedUrl = new URL("http://localhost:8080/poc-webapp-spring-security/vehiclesforperson.rss?id=1");
			//URL feedUrl = new URL(ROOT_TRAFFIC_URL + "appId=" + appId + "&zip=" + zipcode + "&zoom" + zoom + "&severiy=" + severity + "&include_map=0");
			InputStreamReader reader = new InputStreamReader(feedUrl.openStream());
			//InputStreamReader reader = new InputStreamReader(TrafficServiceImpl.class.getClassLoader().getResourceAsStream("trafficInfoExample.rss"));
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(reader);
			
			TrafficInfo[] trafficInfo = new TrafficInfo[feed.getEntries().size()];
			
			int i = 0;
			for(Iterator it = feed.getEntries().iterator(); it.hasNext(); i++) {
				SyndEntryImpl entry = (SyndEntryImpl) it.next();
				trafficInfo[i] = new TrafficInfo();
				trafficInfo[i].setSummary(entry.getTitle());
				trafficInfo[i].setDetails(entry.getDescription().getValue());
				System.out.println(entry.getTitle() + " " + entry.getDescription().getValue());
			} // for
			
			return trafficInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new TrafficInfo[] {};
		}
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}