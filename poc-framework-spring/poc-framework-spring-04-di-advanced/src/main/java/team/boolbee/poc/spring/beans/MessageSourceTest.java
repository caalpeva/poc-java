package team.boolbee.poc.spring.beans;

import java.util.Locale;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import team.boolbee.poc.spring.beans.others.LifecycleDemoBean;
import team.boolbee.poc.spring.beans.performers.Fuddifier;

public class MessageSourceTest {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext ("i18n-context.xml");
		context.registerShutdownHook();

		Locale.setDefault(Locale.ENGLISH);
		System.out.println(context.getMessage("computer", new Object[0],new Locale("en_US")));
		System.out.println(context.getMessage("computer", new Object[0], new Locale("es_MX")));
		System.out.println(context.getMessage("computer", new Object[0], new Locale("es")));
	}
}