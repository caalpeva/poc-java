package core;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.MyBean;

public class Init {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory beanFactory = context;
		MyBean myBean = (MyBean) beanFactory.getBean("myBean");
		System.out.println(myBean.getMensaje());
	}

}
