package team.boolbee.poc.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

public class Init {

	private static final int DEFAULT_PORT = 61616;
	
	private static Connection connection;
	private static Session session;
	private static Queue queue;
	private static Topic topic;
	
	private static Destination destination;
	private static String performerType;
	private static int numMessages = 1;
	private static String messageText;

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:" + DEFAULT_PORT);
		connection = connectionFactory.createConnection();
		queue = new ActiveMQQueue("pocQueue");
		topic = new ActiveMQTopic("pocTopic");
				
		checkArguments(args);
		
		try {
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			if (performerType.equals("consumer")) {
				receiveMessage(connection, destination);
			} else {
				sendMessage(destination);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	private static void sendMessage(Destination destination) throws InterruptedException, JMSException {
		MessageProducer producer = session.createProducer(destination);
		try {
			for (int i = 1; i <= numMessages; ++i) {
				TextMessage message = session.createTextMessage();
				message.setText((numMessages > 1)
						? String.format("%s (%d)", messageText, i)
						: messageText);
				
				//message.getJMSTimestamp()
				System.out.println("Sending message: " + message.getText());
				producer.send(message);
				Thread.sleep(1000);
			} // for
			
			// Sends an empty control message to indicate the end of the message stream
			producer.send(session.createMessage());
		} finally {
			producer.close();
		}
	}
	
	private static void receiveMessage(Connection connection, Destination destination) throws JMSException {
		MessageConsumer consumer = session.createConsumer(destination);
		try {
			connection.start();
			while (true) {
				System.out.println("Waiting for message...");
				Message message = consumer.receive();
				//Message message = consumer.receive(1000);
			    //Message message = consumer.receiveNoWait();
			    if (message != null) {
			    	if (message instanceof TextMessage) {
			    		TextMessage textMessage = (TextMessage) message; 
			    		System.out.println("GOT A MESSAGE: " + textMessage.getText());
			    	} else {
			    		// Because the control message is not a TextMessage, the receiving client terminates the while loop and stops receiving messages after the control message arrives.
			    		break;
			    	}
			    }
			} // while
		} finally {
			connection.stop();
			consumer.close();
		}
	}
	
	private static void checkArguments(String[] args) {
		if (args.length < 2 || args.length > 3) {
			System.err.println("Incorrect number of arguments");
			showCommandFormatAndExit(2);
		}
		
		String destType = args[0];
		if (!(destType.equals("queue") || destType.equals("topic"))) { 
		    System.err.println("Argument must be \"queue\" or \"topic\"");
		    showCommandFormatAndExit(2);
		}
		System.out.println("Destination type is " + destType);
		destination = destType.equals("queue")? queue: topic;
		
		performerType = args[1];
		if (!(performerType.equals("consumer") || performerType.equals("producer"))) { 
		    System.err.println("Argument must be \"consumer\" or \"producer\"");
		    showCommandFormatAndExit(2);
		}
		
		messageText = "Hello World!";
		if (args.length == 3) {
			if (performerType.equals("consumer")) {
				System.err.println("Incorrect number of arguments for consumer");
				showCommandFormatAndExit(2);
			}
			
			try {
				numMessages = Integer.parseInt(args[2]);
			} catch(NumberFormatException e) {
				messageText = args[2];
			}
		}
	}
	
	private static void showCommandFormatAndExit(int status) {
		System.err.println("Syntax:\n\tqueue|topic consumer\n\tqueue|topic producer [text|messages number]");
		System.exit(2);
	}
	
}
