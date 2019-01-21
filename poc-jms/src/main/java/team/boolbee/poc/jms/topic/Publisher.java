package team.boolbee.poc.jms.topic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Publisher implements Runnable {

	Connection conn = null;
	Session session = null;
	MessageProducer publisher;
	String name;
	
	public Publisher(String name, Connection connection, Topic topic) throws JMSException {
		this.name = name;
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		publisher = session.createProducer(topic);
	}
	
     public void run() {
         try {
             for (int i = 1; i <= 5; ++i) {
                 TextMessage message = session.createTextMessage(String.format("¡Hola mundo! (%d)", i));
                 System.out.println(String.format("[%s] Sending message: %s", name, message.getText()));
                 publisher.send(message);
                 Thread.sleep(1000);
             }
         } catch (JMSException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
			e.printStackTrace();
		}
     }
     
     public void close() throws JMSException  {
         publisher.close();
         session.close();
     }
}