package team.boolbee.poc.jms.topic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Subscriber implements MessageListener {

	Connection conn = null;
	Session session = null;
	MessageConsumer consumer;
	String name;
	
	public Subscriber(String name, Connection connection, Topic topic) throws JMSException {
		this.name = name;
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumer = session.createConsumer(topic);
		consumer.setMessageListener(this);
	}
	
    public void onMessage(Message message) {
        try {
            TextMessage text = (TextMessage) message;
            System.out.printf("Suscriptor (%s): El publicador dice: «%s»\n", name, text.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void close() throws JMSException  {
    	 consumer.close();
         session.close();
     }
}