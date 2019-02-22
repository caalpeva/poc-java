package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.others.Contact;

public class PropertyEditorTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("property-editor-context.xml");
		
		Contact contact = (Contact) context.getBean("contact");
		System.out.println("Telephone number: " + contact.getPhoneNumber());
	}
}