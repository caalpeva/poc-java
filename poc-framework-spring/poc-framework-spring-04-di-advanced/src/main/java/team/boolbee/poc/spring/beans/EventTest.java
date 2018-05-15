package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.others.Contact;
import team.boolbee.poc.spring.beans.others.StudentService;
import team.boolbee.poc.spring.beans.performers.Instrumentalist;

public class EventTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("event-context.xml");
		
		StudentService service = (StudentService) context.getBean("studentService");
		service.enrollStudentInCourse("Mathematics", "Egon Spengler");
	}
}