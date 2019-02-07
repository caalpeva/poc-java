package team.boolbee.poc.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AsynchConsumer implements MessageListener {

	MessageConsumer consumer;
	String name;

	public AsynchConsumer(String name, Session session, Destination destination) throws JMSException {
		this.name = name;
		consumer = session.createConsumer(destination);
		consumer.setMessageListener(this);
	}

	public void onMessage(Message message) {
		System.out.println(String.format("onMessage()"));
		if (message != null) {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println(String.format("[%s] Got a message: %s", name, textMessage.getText()));
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void close() throws JMSException {
		consumer.close();
	}
}