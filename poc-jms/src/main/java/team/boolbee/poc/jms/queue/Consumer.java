package team.boolbee.poc.jms.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer implements MessageListener {

	Connection conn = null;
	Session session = null;
	MessageConsumer consumer;
	String name;
	
	public Consumer(String name, Connection connection, Queue destinationQueue) throws JMSException {
		this.name = name;
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumer = session.createConsumer(destinationQueue);
		consumer.setMessageListener(this);
	}

	public void onMessage(Message message) {
		System.out.println(String.format("onMessage()"));
		 if (message != null) {
	    	if (message instanceof TextMessage) {
	    		TextMessage textMessage = (TextMessage) message; 
	    		try {
					System.out.println(String.format("[%s] GOT A MESSAGE: %s", name, textMessage.getText()));
				} catch (JMSException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	 public void close() throws JMSException  {
		 consumer.close();
         session.close();
     }
}