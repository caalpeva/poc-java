package team.boolbee.poc.spring.beans.others;

import org.springframework.context.ApplicationEvent;

public class CourseFullEvent extends ApplicationEvent {

	private String course;
	
	public CourseFullEvent(Object source, String course) {
		super(source);
		this.course = course;
	}
	
	public String getCourse() {
		return course;
	}

}
