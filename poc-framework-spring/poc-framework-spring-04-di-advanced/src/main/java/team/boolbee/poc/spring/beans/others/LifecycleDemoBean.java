package team.boolbee.poc.spring.beans.others;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifecycleDemoBean implements BeanNameAware,
										  BeanFactoryAware,
										  ApplicationContextAware,
										  InitializingBean,
										  DisposableBean {

	private String message;
	
	public LifecycleDemoBean() {
		System.out.println(">> INSTANTIATE");
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		System.out.println(">> POPULATE PROPERTIES");
		this.message = message;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println(">> SET BEAN NAME (" + name + ")");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println(">> SET BEAN FACTORY " + beanFactory);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(">> SET APPLICATION CONTEXT " + applicationContext);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(">> AFTER PROPERTIES");
	}
	
	public void initMethod() {
		System.out.println(">> INIT-METHOD");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(">> DESTROY");
	}
	
	public void destroyMethod() {
		System.out.println(">> DESTROY-METHOD");
	}
}