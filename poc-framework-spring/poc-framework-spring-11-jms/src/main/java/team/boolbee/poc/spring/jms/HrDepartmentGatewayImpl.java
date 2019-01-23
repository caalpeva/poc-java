package team.boolbee.poc.spring.jms;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import team.boolbee.poc.spring.jms.model.Person;

public class HrDepartmentGatewayImpl implements HrDepartmentGateway {

	public void sendPersonInfo(Person person) {
		jmsTemplate.convertAndSend(person);
		//jmsTemplate.convertAndSend(destination, person);
		//jmsTemplate.convertAndSend("jmsQueue", person);
	}

	// injected
	private JmsTemplate jmsTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	@SuppressWarnings("unused")
	private Destination destination;

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}