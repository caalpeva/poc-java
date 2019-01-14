package team.boolbee.poc.spring.security.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehiclesRssView extends AbstractView {

	private String author;
	private String title;
	private String description;
	private String link;
	
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyndFeed feed = createFeed();
		List<Vehicle> vehicles = (List<Vehicle>) model.get("vehicles");
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		
		for(Vehicle vehicle: vehicles) {
			entries.add(createEntry(vehicle));
		}
		
		feed.setEntries(entries);
		
		SyndFeedOutput output = new SyndFeedOutput();
		output.output(feed, response.getWriter());
	}
	
	private SyndFeed createFeed() {
		SyndFeed feed= new SyndFeedImpl();
		feed.setFeedType("rss_1.0");
		feed.setAuthor(author);
		feed.setTitle(title);
		feed.setDescription(description);
		feed.setLink(link);
		
		return feed;
	}
	
	private SyndEntry createEntry(Vehicle vehicle) {
		SyndContent content = new SyndContentImpl();
		content.setType("text/html");
		content.setValue(vehicle.getType() + " " + vehicle.getPlateNumber());
		
		SyndEntry entry = new SyndEntryImpl();
		entry.setTitle("Vehicle entry for " + vehicle.getPlateNumber());
		entry.setLink("http://www.poc-webapp-spring-security.com");
		entry.setPublishedDate(vehicle.getRegistrationDate());
		entry.setDescription(content);
		
		return entry;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}