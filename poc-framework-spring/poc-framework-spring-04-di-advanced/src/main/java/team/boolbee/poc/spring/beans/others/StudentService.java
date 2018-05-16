package team.boolbee.poc.spring.beans.others;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class StudentService implements ApplicationContextAware {

	private ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
	
	public void enrollStudentInCourse(String course, String student) {
		context.publishEvent(new CourseFullEvent(this, course));
	}

}
