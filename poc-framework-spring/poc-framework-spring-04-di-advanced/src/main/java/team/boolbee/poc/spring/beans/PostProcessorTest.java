package team.boolbee.poc.spring.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import team.boolbee.poc.spring.beans.others.LifecycleDemoBean;
import team.boolbee.poc.spring.beans.performers.Fuddifier;

public class PostProcessorTest {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		//BeanFactory context = new XmlBeanFactory(new ClassPathResource("postprocessor-context.xml"));
		//context.addBeanPostProcessor(new Fuddifier());
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext ("postprocessor-context.xml");
		context.registerShutdownHook();

		LifecycleDemoBean bean = (LifecycleDemoBean) context.getBean("lifecycleDemoBean");
		System.out.println(bean.getMessage());
		
//		LifecycleDemoBean bean2 = (LifecycleDemoBean) context.getBean("lifecycleDemoBean2");
//		System.out.println(bean2.getMessage());
	}
}