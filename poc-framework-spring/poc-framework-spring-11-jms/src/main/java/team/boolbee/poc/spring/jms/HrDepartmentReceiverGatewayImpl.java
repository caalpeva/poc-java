package team.boolbee.poc.spring.jms;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import team.boolbee.poc.spring.jms.model.Person;

public class HrDepartmentReceiverGatewayImpl implements HrDepartmentReceiverGateway {

	public Person receivePersonInfo() {
		return (Person) jmsTemplate.receiveAndConvert();
		//return (Person) jmsTemplate.receiveAndConvert(destination);
		//return (Person) jmsTemplate.receiveAndConvert("jmsQueue");
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