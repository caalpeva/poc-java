package team.boolbee.poc.spring.beans;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.performers.Instrumentalist;

public class InitConfigured {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("configured-context.xml");
		
		Instrumentalist pianist = new Instrumentalist();
		pianist.perform();
	}

}
