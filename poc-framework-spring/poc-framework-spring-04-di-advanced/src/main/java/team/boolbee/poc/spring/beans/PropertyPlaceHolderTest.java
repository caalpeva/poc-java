package team.boolbee.poc.spring.beans;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.others.LifecycleDemoBean;

public class PropertyPlaceHolderTest {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		//BeanFactory context = new XmlBeanFactory(new ClassPathResource("postprocessor-context.xml"));
		//context.addBeanPostProcessor(new Fuddifier());
		AbstractApplicationContext  context = new ClassPathXmlApplicationContext ("property-placeholder-context.xml");
		context.registerShutdownHook();

		LifecycleDemoBean bean = (LifecycleDemoBean) context.getBean("lifecycleDemoBean");
		System.out.println(bean.getMessage());
		
//		LifecycleDemoBean bean2 = (LifecycleDemoBean) context.getBean("lifecycleDemoBean2");
//		System.out.println(bean2.getMessage());
	}
}