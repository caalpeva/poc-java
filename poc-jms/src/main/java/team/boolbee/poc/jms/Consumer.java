package team.boolbee.poc.jms;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Consumer {

	private static Log logger = LogFactory.getLog(Consumer.class);

	private MessageConsumer consumer;
	private String name;

	public Consumer(String name, Session session, Destination destination) throws JMSException {
		this.name = name;
		consumer = session.createConsumer(destination);
	}

	public String receive() throws JMSException {
		logger.info(String.format("[%s] Waiting for message...", name));
		return obtainMessageText(consumer.receive());
	}

	public String receive(long timeout) throws JMSException {
		logger.info(String.format("[%s] Waiting %s milliseconds for message...", name, timeout));
		return obtainMessageText(consumer.receive(timeout));
	}

	public String receiveNoWait() throws JMSException {
		logger.info(String.format("[%s] Waiting for message", name));
		return obtainMessageText(consumer.receiveNoWait());
	}

	public void close() throws JMSException {
		consumer.close();
	}
	
	private String obtainMessageText(Message message) throws JMSException {
		if (message != null) {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				logger.info(String.format("[%s] Got a message: %s %s", name, new Date(textMessage.getJMSTimestamp()), textMessage.getText()));
				return textMessage.getText();
			}
		}

		return null;
	}
}