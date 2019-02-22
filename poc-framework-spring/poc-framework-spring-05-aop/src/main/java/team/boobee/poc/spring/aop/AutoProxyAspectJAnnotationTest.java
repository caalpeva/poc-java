package team.boobee.poc.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.TalentCompetition;

public class AutoProxyAspectJAnnotationTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-aop-autoproxy-annotation-aspectj.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
	}
}