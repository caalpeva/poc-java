package team.boobee.poc.framework.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SpringContextTest extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}
	
	public void testBean() {
		MyBean myBean = (MyBean) getApplicationContext().getBean("myBean");
		System.out.println(myBean.getMensaje());
	}
	
	public void testBean2() {
		MyBean myBean = (MyBean) getApplicationContext().getBean("myBean");
		System.out.println(myBean.getMensaje());
	}
	
	@Override
	protected ConfigurableApplicationContext loadContext(Object key) throws Exception {
		System.out.println("loadContext()");
		return super.loadContext(key);
	}
}