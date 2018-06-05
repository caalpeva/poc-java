package team.boobee.poc.framework.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBean {
	private String mensaje; 
	 
	public String getMensaje() {
		return mensaje;
		} 
	 
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
