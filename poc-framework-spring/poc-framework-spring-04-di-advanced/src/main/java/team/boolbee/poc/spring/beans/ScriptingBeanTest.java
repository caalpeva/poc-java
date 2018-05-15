package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.others.Coconut;
import team.boolbee.poc.spring.beans.others.Contact;
import team.boolbee.poc.spring.beans.others.ICoconut;
import team.boolbee.poc.spring.beans.others.StudentService;
import team.boolbee.poc.spring.beans.performers.Instrumentalist;

public class ScriptingBeanTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("scripted-beans-context.xml");
		
		ICoconut coconut = (ICoconut) context.getBean("coconut");
		coconut.drinkThemBothUp();
	}
}