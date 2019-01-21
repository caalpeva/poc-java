package team.boolbee.poc.jms.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer implements Runnable {
	
	Connection conn = null;
	Session session = null;
	MessageProducer producer;
	String name;

	public Producer(String name, Connection connection, Queue destinationQueue) throws JMSException {
		this.name = name;
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(destinationQueue);
	}

	public void run() {
		try {
			for (int i = 1; i <= 5; ++i) {
				TextMessage message = session.createTextMessage();
				message.setText(String.format("Hello World! (%d)", i));
				System.out.println(String.format("[%s] Sending message: %s", name, message.getText()));
				producer.send(message);
				Thread.sleep(1000);
			} // for
		} catch (JMSException e) {
			try {
				close();
			} catch (JMSException ex) {
				// Do nothing
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws JMSException  {
        producer.close();
        session.close();
    }
}