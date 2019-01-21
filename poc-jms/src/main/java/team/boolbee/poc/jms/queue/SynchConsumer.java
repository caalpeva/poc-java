package team.boolbee.poc.jms.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SynchConsumer implements Runnable {

	Connection conn = null;
	Session session = null;
	MessageConsumer consumer;
	String name;
	
	public SynchConsumer(String name, Connection connection, Queue destinationQueue) throws JMSException {
		this.name = name;
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumer = session.createConsumer(destinationQueue);
	}
	
	public void run() {
		try {
			while (true) {
				System.out.println(String.format("[%s] Waiting for message...", name));
			    Message message = consumer.receive();
			    //Message message = consumer.receive(1000);
			    //Message message = consumer.receiveNoWait();
			    if (message != null) {
			    	if (message instanceof TextMessage) {
			    		TextMessage textMessage = (TextMessage) message; 
			    		System.out.println(String.format("[%s] GOT A MESSAGE: %s", name, textMessage.getText()));
			    	}
			    }
			} // while
		} catch(JMSException exception) {
			try {
				close();
			} catch(JMSException ex) {
				// Do nothing
			}
		}
	}

	public void close() throws JMSException  {
		consumer.close();
        session.close();
    }
}