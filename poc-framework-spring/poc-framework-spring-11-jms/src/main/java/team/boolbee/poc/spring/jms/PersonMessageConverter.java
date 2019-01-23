package team.boolbee.poc.spring.jms;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import team.boolbee.poc.spring.jms.model.Person;

public class PersonMessageConverter implements MessageConverter {
	
	public PersonMessageConverter() {}

	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		if (!(message instanceof MapMessage)) {
			throw new MessageConversionException("Message is not a MapMessage");
		}
		
		MapMessage mapMessage = (MapMessage) message;
		Person person = new Person();
		person.setName(mapMessage.getString("name"));
		person.setSurname(mapMessage.getString("surname"));
		person.setBirthDate(new Date(mapMessage.getLong("birthDate")));
		person.setEmail(mapMessage.getString("email"));
		
		return person;
	}

	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		if (!(object instanceof Person)) {
			throw new MessageConversionException("Message is not a Person");
		}
		
		Person person = (Person) object;
		MapMessage mapMessage = session.createMapMessage();
		mapMessage.setString("name", person.getName());
		mapMessage.setString("surname", person.getSurname());
		mapMessage.setLong("birthDate", person.getBirthDate().getTime());
		mapMessage.setString("email", person.getEmail());
		
		return mapMessage;
	}
}