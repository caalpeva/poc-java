package team.boolbee.poc.spring.beans.performers;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Fuddifier implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(String.format("~>> (%s) POST PROCESS BEFORE INITIALIZATION %s",
				Fuddifier.class.getSimpleName(), beanName));
		Field[] fields = bean.getClass().getDeclaredFields();

		try {
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getType().equals(java.lang.String.class)) {
					fields[i].setAccessible(true);
					String original = (String) fields[i].get(bean);
					fields[i].set(bean, fuddify(original));
				}
			} // for
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(String.format("~>> (%s) POST PROCESS AFTER INITIALIZATION %s",
				Fuddifier.class.getSimpleName(), beanName));
		return bean;
	}
	
	private String fuddify(String text) {
		if (text == null) {
			return text;
		}
		
		return text.replaceAll("(r|l)", "w").replaceAll("(R|L)", "W");
	}
}