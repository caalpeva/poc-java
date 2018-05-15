package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("scope-context.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
		System.out.println(context.getBean("cymbal"));
		System.out.println(context.getBean("saxophone"));
	}

}
