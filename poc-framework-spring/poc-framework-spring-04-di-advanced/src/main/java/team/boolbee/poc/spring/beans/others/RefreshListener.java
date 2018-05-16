package team.boolbee.poc.spring.beans.others;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class RefreshListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("Event received");
		
		if (event instanceof CourseFullEvent) {
			CourseFullEvent courseFullEvent = (CourseFullEvent) event;
			System.out.println(String.format("%s course full!!", courseFullEvent.getCourse()));			
		}
	}
}	