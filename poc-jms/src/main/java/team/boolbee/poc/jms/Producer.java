package team.boolbee.poc.jms;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Producer {

	private static Log logger = LogFactory.getLog(Producer.class);
	
	private Session session = null;
	private MessageProducer producer;
	private String name;

	public Producer(String name, Session session, Destination destination) throws JMSException {
		this.name = name;
		this.session = session;
		producer = session.createProducer(destination);
	}

	public void send(String text) throws JMSException {
		TextMessage message = session.createTextMessage(text);
		logger.info(String.format("[%s] Sending message: %s", name, message.getText()));
		producer.send(message);
	}

	public void close() throws JMSException {
		producer.close();
	}
}