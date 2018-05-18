package team.boobee.poc.framework.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class BasicTest extends TestCase {

	ApplicationContext context;
	BeanFactory beanFactory;
	
	@Override
	protected void setUp() throws Exception {
		System.out.println("setUp()");
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		beanFactory = context;
	}
	
	public void testBean() {
		MyBean myBean = (MyBean) beanFactory.getBean("myBean");
		System.out.println(myBean.getMensaje());
	}
	
	public void testBean2() {
		MyBean myBean = (MyBean) beanFactory.getBean("myBean");
		System.out.println(myBean.getMensaje());
	}
}