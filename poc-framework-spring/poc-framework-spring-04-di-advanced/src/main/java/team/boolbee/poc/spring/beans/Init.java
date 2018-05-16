package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.performers.Instrumentalist;

public class Init {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("advanced-context.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
		
//		Instrumentalist instrumentalist = (Instrumentalist) context.getBean("baseSaxophonist");
//		instrumentalist.perform();
	}

}
