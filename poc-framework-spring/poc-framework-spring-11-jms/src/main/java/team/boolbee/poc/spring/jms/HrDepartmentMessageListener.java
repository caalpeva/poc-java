package team.boolbee.poc.spring.jms;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import team.boolbee.poc.spring.jms.model.Person;

public class HrDepartmentMessageListener implements MessageListener {

	public void onMessage(Message message) {
		System.out.println("onMessage()");
		try {
			MapMessage mapMessage = (MapMessage) message;
			Person person = new Person();
			person.setName(mapMessage.getString("name"));
			person.setSurname(mapMessage.getString("surname"));
			person.setBirthDate(new Date(mapMessage.getLong("birthDate")));
			person.setEmail(mapMessage.getString("email"));
			
			processPersonInfo(person);
		} catch(JMSException e) {
			// Do nothing
		}
	}
	
	private void processPersonInfo(Person person) {
		System.out.println(person);
	}
}