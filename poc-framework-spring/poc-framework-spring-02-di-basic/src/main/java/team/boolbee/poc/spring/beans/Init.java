package team.boolbee.poc.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Init {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("team/boolbee/poc/spring/applicationContext.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
	}

}
